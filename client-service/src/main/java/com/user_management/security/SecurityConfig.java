package com.user_management.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.user_management.security.filter.JwtAuthenticationFilter;
import com.user_management.security.filter.JwtAuthorizationFilter;
import com.user_management.security.jwt.JwtUtils;
import com.user_management.service.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserDetailServiceImpl userDetailService;

	@Autowired
	JwtAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager)
			throws Exception {

		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");

		return httpSecurity.csrf(config -> config.disable())
				.authorizeHttpRequests(aut -> {
					aut.requestMatchers("/login").permitAll();
					aut.requestMatchers("/user/create").permitAll();
					aut.requestMatchers("/swagger-ui/index.html").permitAll();
					aut.anyRequest().authenticated();
//					 aut.anyRequest().permitAll();
				}).sessionManagement(session -> {
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				// .httpBasic()
				// .and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();

	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
//		
//		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
//		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
//		
//		
//		return http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource()))
//				.authorizeHttpRequests(aut -> {
//					aut.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//					aut.requestMatchers("/login").permitAll();
//					aut.requestMatchers("/test").permitAll();
//					aut.requestMatchers("/login.html").permitAll();
//					aut.requestMatchers("/index.html").permitAll();
//					aut.requestMatchers("/swagger-ui/index.html#/").permitAll();
//					aut.requestMatchers("/api/products").hasAnyRole("USER", "ADMIN");
//					aut.requestMatchers("/api/find_products").hasRole("USER");
//					aut.requestMatchers("/api/shopping_cart/total_items").permitAll();
//					aut.anyRequest().authenticated();
//				}).sessionManagement(session -> {
//					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//				}).addFilter(jwtAuthenticationFilter).addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class).build();
//	}

//	@Bean
//	UserDetailsService userDetailService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("santiago")
//				.password("$2a$10$RAPepsnPOtUuCA6ffy05uur9sY5TbmJNgy0nIIBUn1EYh.RqM0gaS")
//				.roles()
//				.build());
//		return manager;
//	}
//	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
			throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailService)
				.passwordEncoder(passwordEncoder).and().build();

	}

	// ===========================================================================================

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowCredentials(true);
//		configuration.setAllowedOriginPatterns(List.of("http://localhost:3001")); // tu frontend
//		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//		configuration.setAllowedHeaders(List.of("*"));
//		configuration.setExposedHeaders(List.of("Authorization")); // opcional, si devuelves JWT
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//
//	}
//	

//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration config = new CorsConfiguration();
//
//	    config.setAllowCredentials(true);
//	    config.setAllowedOriginPatterns(List.of("http://localhost:3001")); // tu front
//	    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//	    config.setAllowedHeaders(List.of("*"));
//	    config.setExposedHeaders(List.of("Authorization"));
//
//	    source.registerCorsConfiguration("/**", config);
//
//	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // se ejecuta antes que Spring Security
//	    return bean;
//	}

//	@Bean
//	public CorsFilter corsFilter() {
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    config.addAllowedOrigin("http://localhost:4200");
//	    config.addAllowedHeader("*");
//	    config.addAllowedMethod("*");
//	    source.registerCorsConfiguration("/**", config);
//	    return new CorsFilter(source);
//	}

//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    config.addAllowedOrigin("http://localhost:4200");
//	    config.addAllowedHeader("*");
//	    config.addAllowedMethod("*");
//	    source.registerCorsConfiguration("/**", config);
//
//	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // ⚡ se ejecuta antes que Spring Security
//	    return bean;
//	}

}
