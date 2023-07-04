package com.depromeet.threedays.front.domain.query.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AppleAuthRevokeRequestQuery {

	private String clientId;
	private String clientSecret;
	private String token;
}
