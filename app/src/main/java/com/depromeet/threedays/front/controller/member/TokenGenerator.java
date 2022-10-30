package com.depromeet.threedays.front.controller.member;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenGenerator {
	@Value("${security.jwt.token.secretkey}")
	private final String SECRET_KEY;

	@Value("${security.jwt.token.validtime}")
	private final long TOKEN_VALID_TIME = 120 * 60 * 1000L;

	private static final String AUTHORIZATION_HEADER = "authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	private static final String MEMBER_ID_CLAIM_KEY = "memberId";
	private static final int SUBSTRING_BEARER_INDEX = 7;

	public String generateToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
				.compact();
	}

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

		return !claims.getBody()
					  .getExpiration()
					  .before(new Date());
	}

	private String parseBearerToken(String jwtToken) {
		return jwtToken.substring(SUBSTRING_BEARER_INDEX);
	}
}
