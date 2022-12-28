package com.depromeet.threedays.front.config.security.filter.token;

import org.springframework.security.core.AuthenticationException;

public class AccessTokenInvalidException extends AuthenticationException {
	public AccessTokenInvalidException(String msg) {
		super(msg);
	}
}
