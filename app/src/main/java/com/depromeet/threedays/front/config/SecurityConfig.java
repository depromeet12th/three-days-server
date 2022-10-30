package com.depromeet.threedays.front.config;

import com.depromeet.threedays.front.filter.token.TokenGenerator;
import com.depromeet.threedays.front.filter.token.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
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
	private final AuthenticationProvider tokenAuthenticationProvider;

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

		http.addFilterAt(tokenAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	@Profile(value = "dev")
	public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
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
		TokenAuthenticationFilter tokenAuthenticationFilter =
				new TokenAuthenticationFilter(tokenGenerator);
		tokenAuthenticationFilter.setAuthenticationManager(
				new ProviderManager(tokenAuthenticationProvider));
		return tokenAuthenticationFilter;
	}
}
