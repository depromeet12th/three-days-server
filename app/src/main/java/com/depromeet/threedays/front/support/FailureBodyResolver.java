package com.depromeet.threedays.front.support;

import com.depromeet.threedays.front.support.ApiResponse.FailureBody;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import lombok.experimental.UtilityClass;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@UtilityClass
public class FailureBodyResolver {

	public static ApiResponse.FailureBody resolveFrom(final ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream()
				.map(
						v -> {
							String fieldName = "";
							for (Path.Node node : v.getPropertyPath()) {
								fieldName = node.getName();
							}

							return new ApiResponse.FailureBody(fieldName, v.getMessage());
						})
				.findFirst()
				.orElse(null);
	}

	public static ApiResponse.FailureBody resolveFrom(
			final MissingServletRequestParameterException ex) {
		return new FailureBody(ex.getLocalizedMessage());
	}

	public static ApiResponse.FailureBody resolveFrom(final MethodArgumentTypeMismatchException ex) {
		return new FailureBody(ex.getErrorCode(), ex.getMessage());
	}

	public static ApiResponse.FailureBody resolveFrom(final BindException ex) {
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			return new ApiResponse.FailureBody(error.getCode(), error.getDefaultMessage());
		}
		return null;
	}

	public static ApiResponse.FailureBody resolveFrom(final HttpRequestMethodNotSupportedException ex) {
		return new ApiResponse.FailureBody(ex.getLocalizedMessage());
	}

	public static ApiResponse.FailureBody resolveFrom(final HttpMediaTypeNotSupportedException ex) {
		return new ApiResponse.FailureBody(ex.getLocalizedMessage());
	}

	public static FailureBody resolveFrom(final HttpMediaTypeNotAcceptableException ex) {
		return new ApiResponse.FailureBody(ex.getLocalizedMessage());
	}
}
