package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.domain.query.member.AppleAuthRequestWithTokenQuery;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppleAuthRequestWithTokenConverter {

	public static AppleAuthRequestWithTokenQuery from(String id, String secret, String token) {
		return AppleAuthRequestWithTokenQuery.builder()
				.clientId(id)
				.clientSecret(secret)
				.refreshToken(token)
				.build();
	}
}
