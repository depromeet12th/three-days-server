package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.client.AuthClient;
import com.depromeet.threedays.front.client.model.KeyProperties;
import com.depromeet.threedays.front.client.property.auth.AppleAuthProperty;
import com.depromeet.threedays.front.config.security.filter.token.IdTokenProperties;
import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import com.depromeet.threedays.front.exception.ExternalIntegrationException;
import com.depromeet.threedays.front.exception.JsonParsingException;
import com.depromeet.threedays.front.web.request.member.AppleSignMemberRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenValidator {

	private final TokenResolver tokenResolver;

	private final AuthClient authClient;

	public void validateIdToken(AppleAuthProperty property, AppleSignMemberRequest request) {

		KeyProperties keyProperties = getKeyProperties(property);

		IdTokenProperties idTokenProperties =
				tokenResolver.extractPropertiesByToken(request.getIdToken());

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

		if (!property.getClientId().equals(idTokenProperties.getAud())) {
			throw new JsonParsingException("token.not.valid");
		}
	}

	private KeyProperties getKeyProperties(AppleAuthProperty property) {
		try {
			return authClient.getKeyProperties(new URI(property.getHost() + property.getKeyURI()));
		} catch (URISyntaxException e) {
			throw new ExternalIntegrationException("social.login.error");
		}
	}

	public void validateIdTokenByKeys(KeyProperties keyProperties, String idToken) {
		if (!tokenResolver.verifyPublicKey(keyProperties, idToken)) {
			throw new JsonParsingException("token.not.valid");
		}
	}
}