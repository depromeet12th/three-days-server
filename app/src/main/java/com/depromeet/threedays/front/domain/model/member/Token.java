package com.depromeet.threedays.front.domain.model.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {

	private String accessToken;
	private String idToken;
}
