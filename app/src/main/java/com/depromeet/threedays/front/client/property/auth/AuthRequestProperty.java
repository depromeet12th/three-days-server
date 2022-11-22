package com.depromeet.threedays.front.client.property.auth;

import lombok.Getter;

@Getter
public abstract class AuthRequestProperty {

	protected String uri;

	protected AuthRequestProperty(String uri) {
		this.uri = uri;
	}
}
