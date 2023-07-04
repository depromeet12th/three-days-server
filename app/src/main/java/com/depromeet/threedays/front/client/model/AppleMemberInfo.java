package com.depromeet.threedays.front.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AppleMemberInfo extends MemberInfo {

	private String refreshToken;
}
