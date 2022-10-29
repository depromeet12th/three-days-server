package com.depromeet.threedays.front.client;

import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import com.depromeet.threedays.front.domain.model.Token;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuthClient implements OAuthClient {

	public GoogleOAuthClient() {
		super();
	}

	@Override
	public OAuthInfo getOAuthInfo(OAuthProperty oAuthProperty, Token token) {
		return null;
	}
}
