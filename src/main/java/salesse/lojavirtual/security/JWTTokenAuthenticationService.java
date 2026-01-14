package salesse.lojavirtual.security;

import java.io.IOException;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import salesse.lojavirtual.ApplicationContextLoad;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import salesse.lojavirtual.model.User;
import salesse.lojavirtual.repository.UserRepository;

@Service
public class JWTTokenAuthenticationService {

	/* Token de validade de 11 dias */
	private static final long EXPIRATION_TIME = 959990000;

	/*
	 * Chave de senha para assinar o JWT - Precisa ter pelo menos 256 bits (32
	 * caracteres)
	 */
	private static final String SECRET = "dastr4326546275sad7444s1234567890";

	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";

	public JWTTokenAuthenticationService(ApplicationContextLoad applicationContextLoad) {
	}

	/* Gera o token e dá a resposta para o cliente */
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {

		/* Cria a chave secreta a partir da string */
		SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

		/* Montagem do token */
		String JWT = Jwts.builder().subject(username) // ✅ ATUALIZADO: setSubject() → subject()
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // ✅ ATUALIZADO: setExpiration() →
																					// expiration()
				.signWith(key) // Assina com a chave secreta
				.compact();

		String token = TOKEN_PREFIX + " " + JWT;

		/* Adiciona o token no cabeçalho da resposta HTTP */
		response.addHeader(HEADER_STRING, token);

		liberarCors(response);

		/* Usado para ver no Postman para teste */
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

	/* Retorna o usuário validado com o token ou retorna null se não for validado */
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String token = request.getHeader(HEADER_STRING);

		try {
			if (token != null) {
				String cleanToken = token.replace(TOKEN_PREFIX, "").trim();

				SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

				String user = Jwts.parser().verifyWith(key).build().parseSignedClaims(cleanToken).getPayload()
						.getSubject();

				if (user != null) {
					User userFound = ApplicationContextLoad.getApplicationContext().getBean(UserRepository.class)
							.findUserByLogin(user);

					if (userFound != null) {
						return new UsernamePasswordAuthenticationToken(userFound.getLogin(), null, // ⬅️ nunca retorne a
																									// senha aqui
								userFound.getAuthorities());
					}
				}
			}

		} catch (SecurityException | JwtException e) {
			  System.err.println("Token JWT inválido: " + e.getMessage());

			    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");

			    response.getWriter().write("""
			        {
			          "error": "TOKEN_INVALID",
			          "message": "Token JWT inválido ou expirado"
			        }
			    """);

			    return null;
		} finally {
			liberarCors(response);
		}

		return null;
	}

	/* Liberar acesso CORS */
	private void liberarCors(HttpServletResponse response) {
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}

		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}

		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}

		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
}