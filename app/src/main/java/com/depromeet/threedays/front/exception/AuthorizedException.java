package com.depromeet.threedays.front.exception;

import javax.validation.constraints.NotEmpty;

public class AuthorizedException extends RuntimeException {

	public AuthorizedException(@NotEmpty final String message) {
		super(message);
	}

	public AuthorizedException(@NotEmpty final String message, final Throwable cause) {
		super(message, cause);
	}
}
