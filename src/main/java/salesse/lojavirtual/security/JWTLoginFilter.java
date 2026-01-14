package salesse.lojavirtual.security;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import salesse.lojavirtual.model.User;
import salesse.lojavirtual.ApplicationContextLoad;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url, "POST"));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		try {
			// Obter o usuário do corpo da requisição
			User user = objectMapper.readValue(request.getInputStream(), User.class);

			// Validação básica
			if (user.getLogin() == null || user.getLogin().isEmpty()) {
				throw new BadCredentialsException("Login não pode ser vazio");
			}

			if (user.getPassword() == null || user.getPassword().isEmpty()) {
				throw new BadCredentialsException("Senha não pode ser vazia");
			}

			// Retorna o user com login e senha para autenticação
			return getAuthenticationManager()
					.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

		} catch (IOException e) {
			throw new RuntimeException("Erro ao processar requisição de login", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		try {
			JWTTokenAuthenticationService jwtService = ApplicationContextLoad.getApplicationContext()
					.getBean(JWTTokenAuthenticationService.class);

			jwtService.addAuthentication(response, authResult.getName());

		} catch (Exception e) {
			System.err.println("❌ Erro ao gerar token: " + e.getMessage());
			e.printStackTrace();

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{\"erro\": \"Erro ao gerar token de autenticação\"}");
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		String errorMessage;

		if (failed instanceof BadCredentialsException) {
			errorMessage = "Usuário ou senha incorretos";
		} else {
			errorMessage = "Falha ao realizar login: " + failed.getMessage();
		}

		String jsonResponse = String.format("{\"erro\": \"%s\", \"status\": 401}", errorMessage.replace("\"", "\\\""));

		response.getWriter().write(jsonResponse);
	}
}