package salesse.lojavirtual.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTTokenAuthenticationService {

	/* Token de validade de 11 dias */
	private static final long EXPIRATION_TIME = 959990000;

	/* Chave de senha para assinar o JWT */
	private static final String SECRET = "dastr4326546275sad7444s1234567890"; // Precisa ter pelo menos 256 bits (32
																				// caracteres)
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";

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

		/* Usado para ver no postman para teste */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

}