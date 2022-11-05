package com.depromeet.threedays.front.filter.token;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {
	private static final String ROLE_USER = "ROLE_USER";
	private static final String PREAUTH_TOKEN_CREDENTIAL = "";
	private static final String MEMBER_ID_CLAIM_KEY = "memberId";

	private final TokenResolver tokenResolver;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException, AccessDeniedException {
		final String payload = tokenResolver.decodeTokenPayload(authentication);
		JSONParser jsonParser = new JSONParser();

		Long memberId = null;
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(payload);
			memberId = (Long) jsonObject.get(MEMBER_ID_CLAIM_KEY);
		} catch (ParseException e) {
			throw new AccessDeniedException(e.getMessage());
		}

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
