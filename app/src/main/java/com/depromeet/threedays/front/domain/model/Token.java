package com.depromeet.threedays.front.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
	String accessToken;
	String idToken;
}
