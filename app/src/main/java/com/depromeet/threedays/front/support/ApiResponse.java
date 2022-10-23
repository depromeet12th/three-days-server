package com.depromeet.threedays.front.support;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpEntity;

@Getter
public class ApiResponse<B> extends HttpEntity<B> implements Serializable {

	public ApiResponse() {
		super();
	}

	public ApiResponse(final B body) {
		super(body);
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
}
