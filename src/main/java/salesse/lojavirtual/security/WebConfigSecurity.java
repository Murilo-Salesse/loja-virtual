package salesse.lojavirtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/access/**", "/public/**").permitAll() // Libera
																											// essas
																											// rotas
				.anyRequest().authenticated() // Demais rotas precisam autenticação
		).csrf(csrf -> csrf.disable()); // Desabilita CSRF se necessário (APIs REST)

		return http.build();
	}
}