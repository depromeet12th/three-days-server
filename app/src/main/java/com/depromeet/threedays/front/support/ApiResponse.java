package com.depromeet.threedays.front.support;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<B> extends ResponseEntity<B> implements Serializable {

	public ApiResponse(final HttpStatus status) {
		super(status);
	}

	public ApiResponse(final B body, final HttpStatus status) {
		super(body, status);
	}

	@Getter
	@AllArgsConstructor
	public static class FailureBody implements Serializable {

		private String code;
		private String message;

		public FailureBody(final String message) {
			this.message = message;
		}
	}

	@Getter
	@AllArgsConstructor
	public static class SuccessBody<D> implements Serializable {
		private D data;
		private String message;
		private String code;
	}
}
