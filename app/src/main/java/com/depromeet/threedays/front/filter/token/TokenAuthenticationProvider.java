package com.depromeet.threedays.front.filter.token;

import com.depromeet.threedays.front.domain.usecase.member.GetMemberUseCase;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import java.util.Base64;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {
	private static final String ROLE_USER = "ROLE_USER";
	private static final int PAYLOAD_INDEX = 1;
	private static final String PREAUTH_TOKEN_CREDENTIAL = "";
	private static final String MEMBER_ID_CLAIM_KEY = "memberId";

	private final GetMemberUseCase getMemberUseCase;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String jwtToken = authentication.getPrincipal().toString();

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] split = jwtToken.split("\\.");
		final String payload = new String(decoder.decode(split[PAYLOAD_INDEX].getBytes()));
		JSONParser jsonParser = new JSONParser();

		Long memberId = null;
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(payload);
			Long authenticationMemberId = (Long) jsonObject.get(MEMBER_ID_CLAIM_KEY);
			memberId = getMemberUseCase.execute(authenticationMemberId).getMemberId();
		} catch (ResourceNotFoundException | ParseException e) {
			e.printStackTrace();
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
