package com.depromeet.threedays.front.filter.token;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
	private final TokenGenerator tokenGenerator;

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return tokenGenerator.resolveToken(request);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return tokenGenerator.resolveToken(request);
	}
}
