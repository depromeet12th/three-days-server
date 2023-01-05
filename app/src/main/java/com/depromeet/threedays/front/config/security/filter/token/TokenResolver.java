package com.depromeet.threedays.front.config.security.filter.token;

import io.jsonwebtoken.Jwts;
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
}
