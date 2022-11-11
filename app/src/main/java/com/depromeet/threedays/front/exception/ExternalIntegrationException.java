package com.depromeet.threedays.front.exception;

import com.depromeet.threedays.front.support.MessageSourceAccessor;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ExternalIntegrationException extends RuntimeException {

	private final String code;

	public ExternalIntegrationException(@NotEmpty final String errorCode) {
		super(MessageSourceAccessor.getMessage(errorCode));
		this.code = errorCode;
	}

	public ExternalIntegrationException(@NotEmpty final String errorCode, final Object... args) {
		super(MessageSourceAccessor.getMessage(errorCode, args));
		this.code = errorCode;
	}

	public ExternalIntegrationException(@NotEmpty final String code, final Throwable cause) {
		super(MessageSourceAccessor.getMessage(code), cause);
		this.code = code;
	}

	public ExternalIntegrationException(
			@NotEmpty final String code, final Throwable cause, final Object... args) {
		super(MessageSourceAccessor.getMessage(code, args), cause);
		this.code = code;
	}
}
