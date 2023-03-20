package com.depromeet.threedays.front.support;

import com.depromeet.threedays.front.client.model.KeyProperties;
import com.depromeet.threedays.front.client.model.KeyProperties.KeyProperty;
import com.depromeet.threedays.front.client.property.auth.AppleAuthProperty;
import com.depromeet.threedays.front.config.security.filter.token.IdTokenProperties;
import com.depromeet.threedays.front.config.security.filter.token.TokenResolver;
import com.depromeet.threedays.front.exception.JsonParsingException;
import com.depromeet.threedays.front.web.request.member.AppleSignMemberRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import java.security.interfaces.RSAPublicKey;
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
			AppleAuthProperty property, KeyProperties keyProperties, AppleSignMemberRequest request) {

		IdTokenProperties idTokenProperties = tokenResolver.extractPropertiesByToken(request.getIdToken());

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

		// todo publicKey 검증 방법 조사
		if (!verifyPublicKey(keyProperties, request.getIdToken())) {
			throw new JsonParsingException("token.not.valid");
		}
	}

	private boolean verifyPublicKey(KeyProperties keyProperties, String idToken) {

		try {
			SignedJWT signedJWT = SignedJWT.parse(idToken);

			for (KeyProperty key : keyProperties.getKeys()) {
				ObjectMapper objectMapper = new ObjectMapper();
				RSAKey rsaKey = (RSAKey) JWK.parse(objectMapper.writeValueAsString(key));
				RSAPublicKey publicKey = rsaKey.toRSAPublicKey();
				JWSVerifier verifier = new RSASSAVerifier(publicKey);

				if (signedJWT.verify(verifier)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
