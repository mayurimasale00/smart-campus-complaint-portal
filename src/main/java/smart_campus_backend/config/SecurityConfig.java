package smart_campus_backend.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import smart_campus_backend.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // PUBLIC APIs
                        // =========================

                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()


                        // =========================
                        // ADMIN APIs
                        // =========================

                        // Get all complaints
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/complaints"
                        ).hasRole("ADMIN")

                        // Update complaint status
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/complaints/**"
                        ).hasRole("ADMIN")

                        // Delete complaint
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/complaints/**"
                        ).hasRole("ADMIN")

                        // Add complaint status/history update
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/complaints/*/updates"
                        ).hasRole("ADMIN")


                        // =========================
                        // STUDENT APIs
                        // =========================

                        // Create complaint
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/complaints"
                        ).hasRole("STUDENT")

                        // Get student's complaints
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/complaints/my"
                        ).hasRole("STUDENT")


                        // =========================
                        // COMPLAINT HISTORY
                        // =========================

                        // Logged-in students/admins can
                        // view complaint update history
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/complaints/*/updates"
                        ).authenticated()


                        // =========================
                        // OTHER APIs
                        // =========================

                        .anyRequest().authenticated()
                )


                // =========================
                // SESSION MANAGEMENT
                // =========================

                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                // =========================
                // AUTHENTICATION PROVIDER
                // =========================

                .authenticationProvider(
                        authenticationProvider()
                )


                // =========================
                // JWT FILTER
                // =========================

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }


    // =========================
    // AUTHENTICATION PROVIDER
    // =========================

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(
                passwordEncoder()
        );

        return provider;
    }


    // =========================
    // AUTHENTICATION MANAGER
    // =========================

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }


    // =========================
    // PASSWORD ENCODER
    // =========================

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}