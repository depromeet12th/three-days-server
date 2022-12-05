package com.depromeet.threedays.front.client.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleAuthRequestProperty extends AuthRequestProperty {
	public GoogleAuthRequestProperty(@Value("${google.user.uri}") String uri) {
		super(uri);
	}
}
