package com.depromeet.threedays.front.filter.token;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

	private final TokenResolver tokenResolver;

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return tokenResolver.resolveToken(request);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return tokenResolver.resolveToken(request);
	}
}
