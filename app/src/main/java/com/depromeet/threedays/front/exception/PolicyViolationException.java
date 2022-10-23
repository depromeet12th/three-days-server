package com.depromeet.threedays.front.exception;

import com.depromeet.threedays.front.support.MessageSourceAccessor;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class PolicyViolationException extends RuntimeException {

	private final String code;

	public PolicyViolationException(@NotEmpty final String errorCode) {
		super(MessageSourceAccessor.getMessage(errorCode));
		this.code = errorCode;
	}

	public PolicyViolationException(@NotEmpty final String errorCode, final Object... args) {
		super(MessageSourceAccessor.getMessage(errorCode, args));
		this.code = errorCode;
	}

	public PolicyViolationException(@NotEmpty final String code, final Throwable cause) {
		super(MessageSourceAccessor.getMessage(code), cause);
		this.code = code;
	}

	public PolicyViolationException(
			@NotEmpty final String code, final Throwable cause, final Object... args) {
		super(MessageSourceAccessor.getMessage(code, args), cause);
		this.code = code;
	}
}
