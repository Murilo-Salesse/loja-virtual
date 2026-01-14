package salesse.lojavirtual.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import salesse.lojavirtual.ApplicationContextLoad;

/**
 * Filtro que valida o token JWT em cada requisição
 */
public class JWTApiAutenticacaoFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			// Recupera o serviço do contexto Spring
			JWTTokenAuthenticationService jwtService = ApplicationContextLoad.getApplicationContext()
					.getBean(JWTTokenAuthenticationService.class);

			// Recupera a autenticação do token JWT
			Authentication authentication = jwtService.getAuthentication(request, response);

			// Define a autenticação no contexto do Spring Security
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception e) {
			// Se houver erro na validação, limpa o contexto
			SecurityContextHolder.clearContext();
			// Log opcional (pode remover se não quiser)
			// System.err.println("Erro ao validar token JWT: " + e.getMessage());
		}

		// Continua a cadeia de filtros
		filterChain.doFilter(request, response);
	}
}