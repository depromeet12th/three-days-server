package com.depromeet.threedays.front.support;

import com.depromeet.threedays.front.domain.model.member.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenGenerator {
	@Value("${security.jwt.token.secretkey}")
	private String secretKey;

	@Value("${security.jwt.token.validtime}")
	private Integer tokenValidTime;

	private static final String MEMBER_ID_CLAIM_KEY = "memberId";

	public String generateAccessToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidTime))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}

	public String generateRefreshToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidTime * 10))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}

	public Token generateToken(Long memberId) {
		return Token.builder()
				.accessToken(generateAccessToken(memberId))
				.refreshToken(generateRefreshToken(memberId))
				.build();
	}
}
