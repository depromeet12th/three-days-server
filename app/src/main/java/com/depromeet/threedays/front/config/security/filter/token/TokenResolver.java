package com.depromeet.threedays.front.config.security.filter.token;

import com.depromeet.threedays.front.client.model.KeyProperties;
import com.depromeet.threedays.front.client.model.KeyProperties.KeyProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Jwts;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenResolver {

	private static final int PAYLOAD_INDEX = 1;
	private static final String MEMBER_ID_CLAIM_KEY = "memberId";
	private static final String SUB_CLAIM_KEY = "sub";

	@Value("${security.jwt.token.secretkey}")
	private String secretKey;

	/** 토큰에서 memberId 조회 */
	public Optional<Long> resolveToken(String token) {
		try {
			return Optional.ofNullable(
					Jwts.parserBuilder()
							.setSigningKey(secretKey.getBytes())
							.build()
							.parseClaimsJws(token)
							.getBody()
							.get(MEMBER_ID_CLAIM_KEY, Long.class));
		} catch (Exception e) {
			log.warn("Failed to get memberId. token: {}", token);
			return Optional.empty();
		}
	}

	public IdTokenProperties extractPropertiesByToken(String token) {
		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] split = token.split("\\.");
		String s = new String(decoder.decode(split[PAYLOAD_INDEX].getBytes()));

		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(s);

			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonObject.toJSONString(), IdTokenProperties.class);
		} catch (ParseException e) {
			throw new AccessDeniedException(e.getMessage());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public String extractSubByToken(String token) {
		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] split = token.split("\\.");
		String s = new String(decoder.decode(split[PAYLOAD_INDEX].getBytes()));

		JSONParser jsonParser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
			return (String) jsonObject.get(SUB_CLAIM_KEY);
		} catch (ParseException e) {
			throw new AccessDeniedException(e.getMessage());
		}
	}

	public Long extractIdByToken(String jwtToken) {

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] split = jwtToken.split("\\.");
		String s = new String(decoder.decode(split[PAYLOAD_INDEX].getBytes()));

		JSONParser jsonParser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
			return (Long) jsonObject.get(MEMBER_ID_CLAIM_KEY);
		} catch (ParseException e) {
			throw new AccessDeniedException(e.getMessage());
		}
	}

	public boolean verifyPublicKey(KeyProperties keyProperties, String idToken) {
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
