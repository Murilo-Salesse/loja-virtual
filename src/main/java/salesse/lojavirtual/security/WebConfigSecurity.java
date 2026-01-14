package salesse.lojavirtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import salesse.lojavirtual.service.UserService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

    private final UserService userService;

    public WebConfigSecurity(UserService userService) {
        this.userService = userService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs REST
            
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless para JWT
            ) 
            
            .authorizeHttpRequests(auth -> auth
                // Rotas públicas (sem autenticação)
                .requestMatchers("/login", "/public/**").permitAll()
                
                // Todas as outras rotas requerem autenticação
                .anyRequest().authenticated()
            )
            
            // Adiciona o filtro de login JWT
            .addFilterBefore(
                new JWTLoginFilter("/login", authManager),
                UsernamePasswordAuthenticationFilter.class
            )
            
            // Adiciona o filtro de validação do token JWT
            .addFilterBefore(
                new JWTApiAutenticacaoFilter(),
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}