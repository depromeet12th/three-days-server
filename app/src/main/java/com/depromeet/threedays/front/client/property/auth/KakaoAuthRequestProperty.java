package com.depromeet.threedays.front.client.property.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoAuthRequestProperty extends AuthRequestProperty {
	public KakaoAuthRequestProperty(@Value("${kakao.user.uri}") String uri) {
		super(uri);
	}
}
