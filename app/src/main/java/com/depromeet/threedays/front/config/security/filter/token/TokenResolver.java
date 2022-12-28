package com.depromeet.threedays.front.config.security.filter.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenResolver {

	private static final int PAYLOAD_INDEX = 1;
	private static final String BEARER_PREFIX = "Bearer ";
	private static final int SUBSTRING_BEARER_INDEX = 7;
	private static final String AUTHORIZATION_HEADER = "authorization";

	@Value("${security.jwt.token.secretkey}")
	private String secretKey;

	public String resolveToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(AUTHORIZATION_HEADER);

		if (validateToken(jwtToken)) {
			return parseBearerToken(jwtToken);
		}
		return null;
	}

	/** 토큰에서 memberId 조회 */
	public Optional<Long> resolveToken(String token) {
		try {
			return Optional.ofNullable(
					Jwts.parserBuilder()
							.setSigningKey(secretKey.getBytes())
							.build()
							.parseClaimsJws(token)
							.getBody()
							.get("memberId", Long.class));
		} catch (Exception e) {
			log.warn("Failed to get memberId. token: {}", token);
			return Optional.empty();
		}
	}

	private boolean validateToken(String jwtToken) {
		if (jwtToken == null || !jwtToken.startsWith(BEARER_PREFIX)) {
			return false;
		}

		Jws<Claims> claims =
				Jwts.parserBuilder()
						.setSigningKey(secretKey.getBytes())
						.build()
						.parseClaimsJws(parseBearerToken(jwtToken));

		return !claims.getBody().getExpiration().before(new Date());
	}

	private String parseBearerToken(String jwtToken) {
		return jwtToken.substring(SUBSTRING_BEARER_INDEX);
	}

	public String decodeTokenPayload(Authentication authentication) {
		String jwtToken = authentication.getPrincipal().toString();

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] split = jwtToken.split("\\.");
		return new String(decoder.decode(split[PAYLOAD_INDEX].getBytes()));
	}

	public Long extractIdByToken(String jwtToken) {

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] split = jwtToken.split("\\.");
		String s = new String(decoder.decode(split[PAYLOAD_INDEX].getBytes()));

		JSONParser jsonParser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
			return (Long) jsonObject.get("memberId");
		} catch (ParseException e) {
			throw new AccessDeniedException(e.getMessage());
		}
	}
}
