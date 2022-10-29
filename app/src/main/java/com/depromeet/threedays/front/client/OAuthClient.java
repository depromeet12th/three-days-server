package com.depromeet.threedays.front.client;

import com.depromeet.threedays.front.client.model.OAuthInfo;
import com.depromeet.threedays.front.client.property.OAuthProperty;
import com.depromeet.threedays.front.domain.model.Token;

public interface OAuthClient {
	OAuthInfo getOAuthInfo(OAuthProperty oAuthProperty, Token accessToken);
}
