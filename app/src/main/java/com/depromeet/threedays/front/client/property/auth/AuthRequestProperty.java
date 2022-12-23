package com.depromeet.threedays.front.client.property.auth;

import lombok.Getter;

@Getter
public abstract class AuthRequestProperty {

	protected String host;
	protected String uri;
	protected String unlink;

	protected AuthRequestProperty(String host, String uri, String unlink) {
		this.host = host;
		this.uri = uri;
		this.unlink = unlink;
	}

	public abstract String getAdminKey();
}
