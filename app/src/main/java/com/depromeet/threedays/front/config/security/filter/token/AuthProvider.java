package com.depromeet.threedays.front.config.security.filter.token;

import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

	private static final String ROLE_USER = "ROLE_USER";
	private static final String PREAUTH_TOKEN_CREDENTIAL = "";

	private final TokenResolver tokenResolver;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException, AccessDeniedException {

		Long memberId =
				Optional.ofNullable(authentication.getPrincipal())
						.map(String.class::cast)
						.flatMap(tokenResolver::resolveToken)
						.orElseThrow(
								() ->
										new AccessTokenInvalidException(
												"Invalid access token. accessToken: " + authentication.getPrincipal()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		if (authentication instanceof PreAuthenticatedAuthenticationToken) {
			return new PreAuthenticatedAuthenticationToken(
					memberId,
					PREAUTH_TOKEN_CREDENTIAL,
					Collections.singleton(new SimpleGrantedAuthority(ROLE_USER)));
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
