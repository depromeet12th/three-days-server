package com.depromeet.threedays.front.client.property;

import org.springframework.beans.factory.annotation.Value;

public class KakaoAuthRequestProperty extends AuthRequestProperty {
	public KakaoAuthRequestProperty(@Value("${kakao.user.uri}") String uri) {
		super(uri);
	}
}
