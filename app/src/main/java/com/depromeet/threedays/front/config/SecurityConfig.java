package com.depromeet.threedays.front.config;

import com.depromeet.threedays.front.filter.token.AuthProvider;
import com.depromeet.threedays.front.filter.token.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthProvider authProvider;

	@Bean
	@Profile(value = "local")
	public SecurityFilterChain localSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable();

		http.authorizeRequests()
				.antMatchers("/swagger-ui/index.html#/")
				.permitAll()
				.antMatchers("/api/v1/**")
				.permitAll();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}

	@Bean
	@Profile(value = "prod")
	public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable();

		http.authorizeRequests()
				.antMatchers("/swagger-ui/index.html#/")
				.permitAll()
				.antMatchers("/api/v1/**")
				.permitAll();

		http.addFilterAt(tokenAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();
		tokenAuthenticationFilter.setAuthenticationManager(new ProviderManager(authProvider));
		return tokenAuthenticationFilter;
	}
}
