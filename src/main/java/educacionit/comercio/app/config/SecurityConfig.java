package educacionit.comercio.app.config;

import educacionit.comercio.app.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
public class SecurityConfig{

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.requestMatchers("/inicio","/css/**","/js/**","/images/**",  "/WEB-INF/views/**").permitAll()
                    .requestMatchers("/h2-**")
                    .permitAll()
                    .requestMatchers("/admin")
                    .hasRole("ADMIN")
                    .requestMatchers("/productos")
                    .hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE,"/invoice/delete")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .authenticated())
                .headers(header -> header.frameOptions(options -> options.disable()))
            .formLogin(formLogin ->
                    formLogin
                            .loginPage("/login")
                            .permitAll() // Permitir acceso público a la página de inicio de sesión
            )
            .logout(logout -> logout.permitAll() // Permitir acceso público al proceso de cierre de sesión
            )
            .exceptionHandling(exceptionHandling ->
                    exceptionHandling
                            .accessDeniedPage("/error_403") // Página de acceso denegado personalizada
            )
            .sessionManagement(sessionManagement ->
                    sessionManagement
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Política de creación de sesión según sea necesario
            )
                .rememberMe( remember -> remember.alwaysRemember(Boolean.TRUE));

        return http.build();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("pepito")
                .password(bCryptPasswordEncoder.encode("jose123"))
                .roles("USER")
                .build()
        );

        manager.createUser(User.withUsername("administrador")
                .password(bCryptPasswordEncoder.encode("admin1234."))
                .roles("ADMIN")
                .build()
        );

        manager.createUser(User.withUsername("adminYuser")
                .password(bCryptPasswordEncoder().encode("jose123"))
                .roles("ADMIN", "USER")
                .build()
        );
        return manager;
    }
     */



    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}