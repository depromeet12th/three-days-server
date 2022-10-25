package com.depromeet.threedays.front.client.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuthProperty extends OAuthProperty {
	public GoogleOAuthProperty(
			@Value("${google.web.client.id}") String clientId,
			@Value("${google.web.client.secret}") String clientSecret,
			@Value("${google.web.redirect.uri}") String redirectUri,
			@Value("${google.access.token.uri}") String accessTokenUri,
			@Value("${google.user.uri}") String userUri) {
		super(redirectUri, clientId, clientSecret, accessTokenUri, userUri);
	}
}
