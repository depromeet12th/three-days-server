package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.client.model.KeyProperties;
import com.depromeet.threedays.front.client.property.auth.AppleAuthProperty;
import com.depromeet.threedays.front.config.security.filter.token.IdTokenProperties;
import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import com.depromeet.threedays.front.exception.JsonParsingException;
import com.depromeet.threedays.front.web.request.member.AppleSignMemberRequest;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenValidator {

	private final TokenResolver tokenResolver;

	public void validateIdToken(
			AppleAuthProperty property,
			AppleSignMemberRequest request,
			IdTokenProperties idTokenProperties) {

		Date currentTime = new Date(System.currentTimeMillis());
		if (!currentTime.before(idTokenProperties.getExp())) {
			throw new JsonParsingException("token.not.valid");
		}

		if (!request.getNonce().equals(idTokenProperties.getNonce())) {
			throw new JsonParsingException("token.not.valid");
		}

		if (!property.getHost().equals(idTokenProperties.getIss())) {
			throw new JsonParsingException("token.not.valid");
		}

		if (!property.getServiceId().equals(idTokenProperties.getAud())) {
			throw new JsonParsingException("token.not.valid");
		}
	}

	public void validateIdTokenByKeys(KeyProperties keyProperties, String idToken) {
		if (!tokenResolver.verifyPublicKey(keyProperties, idToken)) {
			throw new JsonParsingException("token.not.valid");
		}
	}
}
