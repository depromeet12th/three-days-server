package com.depromeet.threedays.front.config.security.filter.token;

import com.depromeet.threedays.front.client.property.auth.AppleAuthProperty;
import com.depromeet.threedays.front.domain.model.member.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
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

	@Value("${security.jwt.token.validtime.access}")
	private Long accessTokenValidTime;

	@Value("${security.jwt.token.validtime.refresh}")
	private Long refreshTokenValidTime;

	@Value("${security.jwt.token.validtime.clientSecret}")
	private Long clientSecretValidTime;

	private static final String MEMBER_ID_CLAIM_KEY = "memberId";
	private static final String HEADER_KID = "kid";

	public String generateAccessToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + accessTokenValidTime))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}

	String generateRefreshToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + refreshTokenValidTime))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}

	public String generateClientSecret(AppleAuthProperty property) {
		Date now = new Date();
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(property.getPrivateKey());
		ECPrivateKey ecPrivateKey;

		try {
			KeyFactory kf = KeyFactory.getInstance("EC");
			ecPrivateKey = (ECPrivateKey) kf.generatePrivate(spec);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return Jwts.builder()
				.setHeaderParam(HEADER_KID, property.getKeyId())
				.setIssuer(property.getTeamId())
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + clientSecretValidTime))
				.setAudience(property.getHost())
				.setSubject(property.getServiceId())
				.signWith(ecPrivateKey)
				.compact();
	}

	public Token generateToken(Long memberId) {
		return Token.builder()
				.accessToken(generateAccessToken(memberId))
				.refreshToken(generateRefreshToken(memberId))
				.build();
	}
}
