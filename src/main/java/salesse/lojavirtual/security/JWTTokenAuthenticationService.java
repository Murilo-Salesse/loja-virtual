package salesse.lojavirtual.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import salesse.lojavirtual.ApplicationContextLoad;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import salesse.lojavirtual.model.User;
import salesse.lojavirtual.repository.UserRepository;

@Service
public class JWTTokenAuthenticationService {

	private final ApplicationContextLoad applicationContextLoad;

	/* Token de validade de 11 dias */
	private static final long EXPIRATION_TIME = 959990000;

	/* Chave de senha para assinar o JWT */
	private static final String SECRET = "dastr4326546275sad7444s1234567890"; // Precisa ter pelo menos 256 bits (32
																				// caracteres)
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";

	JWTTokenAuthenticationService(ApplicationContextLoad applicationContextLoad) {
		this.applicationContextLoad = applicationContextLoad;
	}

	/* Gera o token e dá a resposta para o cliente */
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {

		/* Cria a chave secreta a partir da string */
		SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

		/* Montagem do token */
		String JWT = Jwts.builder() /* Chama o gerador de token */
				.setSubject(username) /* ADD user */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /* Tempo de expiração */
				.signWith(key) /* Assina com a chave secreta */
				.compact();

		String token = TOKEN_PREFIX + " " + JWT;

		/* Adiciona o token no cabeçalho da resposta HTTP, EX: Bearer (token) */
		response.addHeader(HEADER_STRING, token);

		fixCors(response);

		/* Usado para ver no postman para teste */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

	/* Retorna o user validado com o token ou retorna null se nao for validado */
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			String cleanToken = token.replace(TOKEN_PREFIX, "").trim();

			/* Cria a chave secreta */
			SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

			/* Faz a validação do token */
			String user = Jwts.parserBuilder() // Use parserBuilder() em vez de parser()
					.setSigningKey(key) // Aqui usa setSigningKey() mas com SecretKey
					.build().parseClaimsJws(cleanToken).getBody().getSubject();

			if (user != null) {
				User userFound = applicationContextLoad.getApplicationContext().getBean(UserRepository.class)
						.findUserByLogin(user);

				if (userFound != null) {
					return new UsernamePasswordAuthenticationToken(userFound.getLogin(), userFound.getPassword(),
							userFound.getAuthorities());
				}
			}

		}

		fixCors(response);
		return null;
	}

	/* Liberar acesso CORS */
	private void fixCors(HttpServletResponse resp) {
		if (resp.getHeader("Access-Control-Allow-Origin") == null) {
			resp.addHeader("Access-Control-Allow-Origin", "*");
		}

		if (resp.getHeader("Access-Control-Allow-Headers") == null) {
			resp.addHeader("Access-Control-Allow-Headers", "*");
		}

		if (resp.getHeader("Access-Control-Request-Headers") == null) {
			resp.addHeader("Access-Control-Request-Headers", "*");
		}

		if (resp.getHeader("Access-Control-Allow-Methods") == null) {
			resp.addHeader("Access-Control-Allow-Methods", "*");
		}
	}

}