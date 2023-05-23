package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.domain.query.member.AppleAuthRevokeRequestQuery;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppleAuthRevokeRequestConverter {

	public static AppleAuthRevokeRequestQuery from(String id, String secret, String token) {
		return AppleAuthRevokeRequestQuery.builder()
				.clientId(id)
				.clientSecret(secret)
				.token(token)
				.build();
	}
}
