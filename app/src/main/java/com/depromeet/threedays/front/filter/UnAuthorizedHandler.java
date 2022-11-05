package com.depromeet.threedays.front.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
@RequiredArgsConstructor
public class UnAuthorizedHandler implements AuthenticationEntryPoint {
	private final HandlerExceptionResolver handlerExceptionResolver;

	@Override
	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException) {
		handlerExceptionResolver.resolveException(request, response, null, authException);
	}
}
