//package com.api_gateway.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;

//@EnableWebFluxSecurity
//@Configuration
//public class SecurityConfig {
//    @Bean
//    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http.csrf(csrf -> csrf.disable()
//                        .authorizeExchange(exchange -> exchange
//                                .pathMatchers("/auth/**", "/actuator/**").permitAll()
//                                .anyExchange().authenticated()))
//                .oauth2ResourceServer(server -> server.jwt());
//        return http.build();
//    }
//}
