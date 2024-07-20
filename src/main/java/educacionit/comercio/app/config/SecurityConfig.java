package educacionit.comercio.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig{

   // @Autowired
    //private AuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.requestMatchers("/inicio","/css/**","/js/**","/images/**",  "/WEB-INF/views/**").permitAll()
                    .anyRequest()
                    .authenticated())
            .formLogin(formLogin ->
                    formLogin
                            .loginPage("/login") // Página personalizada de inicio de sesión
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/inicio", true)
                            .failureUrl("/login?error=true")
                            .permitAll() // Permitir acceso público a la página de inicio de sesión
                            //.successHandler(successHandler) // Manejador personalizado para el éxito del inicio de sesión
            )
            .logout(LogoutConfigurer::permitAll // Permitir acceso público al proceso de cierre de sesión
            )
            .exceptionHandling(exceptionHandling ->
                    exceptionHandling
                            .accessDeniedPage("/error_403") // Página de acceso denegado personalizada
            )
            .sessionManagement(sessionManagement ->
                    sessionManagement
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Política de creación de sesión según sea necesario
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}