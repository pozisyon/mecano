package com.mecano.assistance.infrastructure.security;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // PUBLIC
                        .requestMatchers(
                                "/",
                                "/api/auth/**",
                                "/oauth2/**",
                                "/api/public/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/actuator/health",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers("/api/interventions/my")
                        .hasAnyRole("DRIVER", "ADMIN")
                        .requestMatchers("/api/payments/my")
                        .hasAnyRole("DRIVER", "ADMIN")
                        .requestMatchers("/api/invoices/my")
                        .hasAnyRole("DRIVER", "ADMIN")
                        .requestMatchers("/api/mechanics/interventions/history")
                        .hasAnyRole("MECHANIC", "ADMIN")

                                // ADMIN - MECHANIC VALIDATION
                                .requestMatchers("/api/admin/mechanics/pending")
                                .hasAuthority("PERMISSION_MECHANIC_READ")

                                .requestMatchers("/api/admin/mechanics/*/approve")
                                .hasAuthority("PERMISSION_MECHANIC_VALIDATE")

                                .requestMatchers("/api/admin/mechanics/*/reject")
                                .hasAuthority("PERMISSION_MECHANIC_REJECT")


                        // WEBSOCKET
                        .requestMatchers("/ws/**")
                        .authenticated()

                        // DRIVER
                        .requestMatchers("/api/vehicles/**")
                        .hasAnyRole("DRIVER", "ADMIN")

                        .requestMatchers("/api/breakdowns/**")
                        .hasAnyRole("DRIVER", "ADMIN")

                        // MECHANIC / GARAGE
                        .requestMatchers("/api/mechanics/**")
                        .hasAnyRole("MECHANIC", "GARAGE_ADMIN", "ADMIN")

                        .requestMatchers("/api/interventions/**")
                        .hasAnyRole("MECHANIC", "GARAGE_ADMIN", "ADMIN")

                        // PAYMENTS / INVOICES
                        .requestMatchers("/api/payments/**")
                        .hasAnyRole("DRIVER", "ADMIN", "FINANCE_AGENT", "FINANCE_MANAGER")

                        .requestMatchers("/api/invoices/**")
                        .hasAnyRole("DRIVER", "ADMIN", "FINANCE_AGENT", "FINANCE_MANAGER")

                        // CHAT
                        .requestMatchers("/api/chat/**")
                        .hasAnyRole("DRIVER", "MECHANIC", "GARAGE_ADMIN", "ADMIN")

                        // REVIEWS
                        .requestMatchers("/api/reviews/**")
                        .hasAnyRole("DRIVER", "MECHANIC", "GARAGE_ADMIN", "ADMIN")

                        // DISPUTES / SUPPORT
                        .requestMatchers("/api/disputes/**")
                        .hasAnyRole("DRIVER", "MECHANIC", "GARAGE_ADMIN", "ADMIN", "SUPPORT_AGENT", "SUPPORT_MANAGER")

                        // DOCUMENTS
                        .requestMatchers("/api/documents/**")
                        .hasAnyRole("MECHANIC", "GARAGE_ADMIN", "ADMIN", "SUPPLIER_VALIDATION_AGENT", "SUPPLIER_VALIDATION_MANAGER")

                        // NOTIFICATIONS
                        .requestMatchers("/api/notifications/**")
                        .authenticated()

                        .requestMatchers("/api/device-tokens/**")
                        .authenticated()

                        // ADMIN
                        .requestMatchers("/api/admin/**")
                        .hasAnyRole(
                                "ADMIN",
                                "SYSTEM_ADMIN",
                                "OPERATIONS_AGENT",
                                "OPERATIONS_MANAGER",
                                "SUPPORT_AGENT",
                                "SUPPORT_MANAGER",
                                "FINANCE_AGENT",
                                "FINANCE_MANAGER",
                                "SUPPLIER_VALIDATION_AGENT",
                                "SUPPLIER_VALIDATION_MANAGER"
                        )

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://localhost:3000",
                "http://localhost:8100",
                "http://localhost:8101",
                "capacitor://localhost",
                "http://localhost", "https://localhost",
                "https://garagefrontend-lrfy.onrender.com"
        ));

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}