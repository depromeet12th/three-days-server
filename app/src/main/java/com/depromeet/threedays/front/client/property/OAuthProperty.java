package com.depromeet.threedays.front.client.property;

import lombok.Getter;

@Getter
public abstract class OAuthProperty {

	protected String userUri;

	protected OAuthProperty(
			String userUri) {
		this.userUri = userUri;
	}
}
