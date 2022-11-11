package com.depromeet.threedays.front.client.property;

import lombok.Getter;

@Getter
public abstract class AuthRequestProperty {

	protected String uri;

	protected AuthRequestProperty(String userUri) {
		this.uri = userUri;
	}
}
