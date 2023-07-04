package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.client.model.KeyProperties;
import com.depromeet.threedays.front.client.property.auth.AppleAuthProperty;
import com.depromeet.threedays.front.config.security.filter.token.IdTokenProperties;
import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import com.depromeet.threedays.front.web.request.member.AppleSignMemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenAuthenticator {

	private final TokenValidator tokenValidator;
	private final TokenResolver tokenResolver;

	public void authenticateIdToken(
			AppleAuthProperty property, KeyProperties keyProperties, AppleSignMemberRequest request) {
		tokenValidator.validateIdTokenByKeys(keyProperties, request.getIdToken());
		IdTokenProperties idTokenProperties =
				tokenResolver.extractPropertiesByToken(request.getIdToken());
		tokenValidator.validateIdToken(property, request, idTokenProperties);
	}
}
