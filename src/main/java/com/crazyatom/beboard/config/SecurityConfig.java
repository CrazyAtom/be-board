package com.crazyatom.beboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crazyatom.beboard.jwt.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
			.httpBasic(httpBasic -> httpBasic.disable())
			.formLogin(formLogin -> formLogin.disable())
			.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/post/**").permitAll()
				.requestMatchers("/api/**").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/v3/api-docs/**").permitAll()
				.requestMatchers("/swagger-resources/**").permitAll()
				.anyRequest().authenticated()
			)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
