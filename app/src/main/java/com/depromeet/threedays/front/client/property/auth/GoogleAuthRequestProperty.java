package com.depromeet.threedays.front.client.property.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleAuthRequestProperty extends AuthRequestProperty {
	public GoogleAuthRequestProperty(
			@Value("${google.host}") String host, @Value("${google.user.uri}") String uri) {
		super(host, uri, "");
	}

	@Override
	public String getAdminKey() {
		return null;
	}
}
