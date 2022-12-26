package com.depromeet.threedays.front.client.property.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoAuthRequestProperty extends AuthRequestProperty {

	@Value("${kakao.adminKey}")
	String adminKey;

	public KakaoAuthRequestProperty(
			@Value("${kakao.host}") String host,
			@Value("${kakao.user.uri}") String uri,
			@Value("${kakao.unlink.uri}") String unlink) {
		super(host, uri, unlink);
	}
}
