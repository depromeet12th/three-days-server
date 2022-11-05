package com.depromeet.threedays.front.client.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuthProperty extends OAuthProperty {
	public GoogleOAuthProperty(
			@Value("${google.user.uri}") String userUri) {
		super(userUri);
	}
}
