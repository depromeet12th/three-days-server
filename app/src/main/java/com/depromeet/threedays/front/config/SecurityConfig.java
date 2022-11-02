package com.depromeet.threedays.front.config;

import com.depromeet.threedays.front.filter.TokenAccessDeniedHandler;
import com.depromeet.threedays.front.filter.UnAuthorizedHandler;
import com.depromeet.threedays.front.filter.token.AuthProvider;
import com.depromeet.threedays.front.filter.token.AuthenticationFilter;
import com.depromeet.threedays.front.filter.token.TokenResolver;
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

	private final UnAuthorizedHandler unAuthorizedHandler;
	private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
	private final AuthProvider authProvider;
	private final TokenResolver tokenResolver;

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

		http.exceptionHandling()
			.authenticationEntryPoint(unAuthorizedHandler)
			.accessDeniedHandler(tokenAccessDeniedHandler);

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
				.permitAll()
				.anyRequest()
				.authenticated();

		http.addFilterAt(
				generateAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	public AuthenticationFilter generateAuthenticationFilter() {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(tokenResolver);
		authenticationFilter.setAuthenticationManager(new ProviderManager(authProvider));
		return authenticationFilter;
	}
}
