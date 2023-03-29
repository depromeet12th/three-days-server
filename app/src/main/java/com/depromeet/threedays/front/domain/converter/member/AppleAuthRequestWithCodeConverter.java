package com.depromeet.threedays.front.domain.converter.member;

import com.depromeet.threedays.front.domain.query.member.AppleAuthRequestWithCodeQuery;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppleAuthRequestWithCodeConverter {

	public static AppleAuthRequestWithCodeQuery from(String id, String secret, String code) {
		return AppleAuthRequestWithCodeQuery.builder()
				.clientId(id)
				.clientSecret(secret)
				.code(code)
				.build();
	}
}
