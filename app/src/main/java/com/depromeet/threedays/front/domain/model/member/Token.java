package com.depromeet.threedays.front.domain.model.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Token {

	private String accessToken;
	private String refreshToken;
}
