package com.depromeet.threedays.front.support;

import com.depromeet.threedays.front.support.ApiResponse.FailureBody;

public class ApiResponseGenerator {

	private ApiResponseGenerator() {
		throw new UnsupportedOperationException();
	}

	public static ApiResponse<Void> success() {
		return new ApiResponse<>();
	}

	public static <D> ApiResponse<D> success(final D data) {
		return new ApiResponse<>(data);
	}

	public static <D> ApiResponse<Page<D>> success(
			final org.springframework.data.domain.Page<D> data) {
		return new ApiResponse<>(new Page<>(data));
	}

	public static ApiResponse<Void> fail() {
		return new ApiResponse<>();
	}

	public static ApiResponse<ApiResponse.FailureBody> fail(final ApiResponse.FailureBody body) {
		return new ApiResponse<>(body);
	}

	public static ApiResponse<ApiResponse.FailureBody> fail(final String code, final String message) {
		return new ApiResponse<>(new FailureBody(code, message));
	}
}
