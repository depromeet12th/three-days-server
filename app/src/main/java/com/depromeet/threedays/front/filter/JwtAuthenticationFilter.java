package com.depromeet.threedays.front.filter;

import com.depromeet.threedays.front.config.JwtTokenProvider;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return jwtTokenProvider.resolveToken(request);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return jwtTokenProvider.resolveToken(request);
	}
}
