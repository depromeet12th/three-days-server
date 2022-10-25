package com.depromeet.threedays.front.client.property;

import lombok.Getter;

@Getter
public abstract class OAuthProperty {

	protected String redirectUri;

	protected String clientId;

	protected String clientSecret;
	protected String accessTokenUri;

	protected String userUri;

	public OAuthProperty(
			String redirectUri,
			String clientId,
			String clientSecret,
			String accessTokenUri,
			String userUri) {
		this.redirectUri = redirectUri;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.accessTokenUri = accessTokenUri;
		this.userUri = userUri;
	}
}
