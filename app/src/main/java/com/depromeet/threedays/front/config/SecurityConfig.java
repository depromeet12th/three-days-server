package com.depromeet.threedays.front.config;

import com.depromeet.threedays.front.controller.member.TokenAuthenticationProvider;
import com.depromeet.threedays.front.controller.member.TokenGenerator;
import com.depromeet.threedays.front.filter.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final TokenGenerator tokenGenerator;
	private final TokenAuthenticationProvider tokenAuthenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable();

		http.authorizeRequests()
				.antMatchers("/swagger-ui/index.html#/")
				.permitAll()
				.antMatchers("/api/v1/**")
				.permitAll();

		http.addFilterAt(jwtAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	public TokenAuthenticationFilter jwtAuthenticationFilter() {
		TokenAuthenticationFilter tokenAuthenticationFilter =
				new TokenAuthenticationFilter(tokenGenerator);
		tokenAuthenticationFilter.setAuthenticationManager(
				new ProviderManager(tokenAuthenticationProvider));
		return tokenAuthenticationFilter;
	}
}
