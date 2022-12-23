package com.depromeet.threedays.front.client.property.auth;

import lombok.Getter;

@Getter
public abstract class AuthRequestProperty {

	protected String host;
	protected String uri;

	protected AuthRequestProperty(String host, String uri) {
		this.host = host;
		this.uri = uri;
	}
}
