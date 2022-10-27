package com.depromeet.threedays.front.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider implements AuthenticationProvider {
	private static final String SECRET_KEY = "ThreeDaysSecretKeyThreeDaysSecretKey";
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	private static final long TOKEN_VALID_TIME = 30 * 60 * 1000L;
	private static final String MEMBER_ID_CLAIM_KEY = "memberId";
	private static final String ROLE_USER = "ROLE_USER";
	private static final int PAYLOAD_INDEX = 1;
	private static final int SUBSTRING_BEARER_INDEX = 7;
	private static final String PREAUTH_TOKEN_CREDENTIAL = "";
	private Key key;

	@PostConstruct
	protected void encodeSecretKey() {
		this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String generateToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
				.signWith(key)
				.compact();
	}

	public String resolveToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(AUTHORIZATION_HEADER);
		System.out.println(jwtToken);

		if (validateToken(jwtToken)) {
			return parseBearerToken(jwtToken);
		}
		return null;
	}

	private boolean validateToken(String jwtToken) {
		if (jwtToken != null && jwtToken.startsWith(BEARER_PREFIX)) {
			Jws<Claims> claims =
					Jwts.parserBuilder()
							.setSigningKey(SECRET_KEY.getBytes())
							.build()
							.parseClaimsJws(parseBearerToken(jwtToken));

			if (!claims.getBody().getExpiration().before(new Date())) {
				return true;
			}
			return false;
		}
		return false;
	}

	private String parseBearerToken(String jwtToken) {
		return jwtToken.substring(SUBSTRING_BEARER_INDEX);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String jwtToken = authentication.getPrincipal().toString();
		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] split = jwtToken.split("\\.");
		final String payload = new String(decoder.decode(split[PAYLOAD_INDEX].getBytes()));

		JSONParser jsonParser = new JSONParser();
		Long memberId;
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(payload);
			memberId = (Long) jsonObject.get(MEMBER_ID_CLAIM_KEY);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		if (authentication instanceof PreAuthenticatedAuthenticationToken && validateToken(jwtToken)) {
			return new PreAuthenticatedAuthenticationToken(
					memberId, PREAUTH_TOKEN_CREDENTIAL, Collections.singleton(new SimpleGrantedAuthority(ROLE_USER)));
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
