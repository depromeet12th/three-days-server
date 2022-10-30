package com.depromeet.threedays.front.filter.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

	@Value("${security.jwt.token.secretkey}")
	private String SECRET_KEY;

	private static final String BEARER_PREFIX = "Bearer ";
	private static final int SUBSTRING_BEARER_INDEX = 7;
	private static final String AUTHORIZATION_HEADER = "authorization";

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return resolveToken(request);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return resolveToken(request);
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

		return !claims.getBody().getExpiration().before(new Date());
	}

	private String parseBearerToken(String jwtToken) {
		return jwtToken.substring(SUBSTRING_BEARER_INDEX);
	}
}
