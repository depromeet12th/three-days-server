package com.depromeet.threedays.front.filter.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

public class TokenResolver {
	@Value("${security.jwt.token.secretkey}")
	private String SECRET_KEY;

	private static final int PAYLOAD_INDEX = 1;

	private static final String BEARER_PREFIX = "Bearer ";
	private static final int SUBSTRING_BEARER_INDEX = 7;
	private static final String AUTHORIZATION_HEADER = "authorization";

	public String resolveToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(AUTHORIZATION_HEADER);

		if (validateToken(jwtToken)) {
			return parseBearerToken(jwtToken);
		}
		return null;
	}

	private boolean validateToken(String jwtToken) {
		if (jwtToken == null || !jwtToken.startsWith(BEARER_PREFIX)) {
			return false;
		}

		Jws<Claims> claims =
				Jwts.parserBuilder()
						.setSigningKey(SECRET_KEY.getBytes())
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
}
