package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.exception.JsonParsingException;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.support.FailureBodyResolver;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ApiControllerExceptionHandler {

	private static final String LOG_MESSAGE_FORMAT = "{} '{}' - {}";
	private static final String UNCAUGHT_LOG_MESSAGE = "??";

	@ExceptionHandler(IllegalArgumentException.class)
	public final ApiResponse<Void> handleBadRequest(
			final IllegalArgumentException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PolicyViolationException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final PolicyViolationException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(ex.getCode(), ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final TypeMismatchException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final ServletRequestBindingException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final BindException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final ConstraintViolationException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final HttpRequestMethodNotSupportedException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final HttpMediaTypeNotSupportedException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final HttpMediaTypeNotAcceptableException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final HttpMessageNotReadableException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final MissingServletRequestPartException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ResourceNotFoundException.class, NoHandlerFoundException.class})
	public ApiResponse<Void> handleNotFound(final Exception ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({AccessDeniedException.class})
	public ApiResponse<Void> handleForbidden(
			final AccessDeniedException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({JsonParsingException.class})
	public ApiResponse<Void> handleJson(final JsonParsingException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse<Void> handleInternalServerError(final Exception ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private <E extends Exception> void writeLog(final E ex, final WebRequest webRequest) {
		try {
			HttpServletRequest servletRequest = ((ServletWebRequest) webRequest).getRequest();
			log.error(
					LOG_MESSAGE_FORMAT,
					servletRequest.getMethod(),
					servletRequest.getRequestURI(),
					ex.getMessage(),
					ex);
		} catch (Exception e) {
			log.error(LOG_MESSAGE_FORMAT, UNCAUGHT_LOG_MESSAGE, UNCAUGHT_LOG_MESSAGE, e.getMessage(), e);
		}
	}
}
