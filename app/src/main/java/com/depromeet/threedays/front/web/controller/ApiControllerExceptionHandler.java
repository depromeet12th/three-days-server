package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.exception.*;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiControllerExceptionHandler {

	private static final String FAIL_CODE = "fail";

	private static final String BAD_REQUEST_MESSAGE = "알 수 없는 오류가 발생했어요.";

	private static final String REFRESH_TOKEN_INVALID_MESSAGE = "로그인이 필요해요.";

	private static final String FORBIDDEN_MESSAGE = "접근 권한이 없어요.";

	private static final String UNAUTHORIZED_MESSAGE = "인증이 필요해요.";

	private static final String NOT_FOUND_MESSAGE = "요청과 일치하는 결과를 찾을 수 없어요.";

	private static final String SERVER_ERROR_MESSAGE = "알 수 없는 오류가 발생했어요.";

	private final LoggingHandler loggingHandler;

	@ExceptionHandler(IllegalArgumentException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final IllegalArgumentException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(FAIL_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({
		MethodArgumentTypeMismatchException.class,
		TypeMismatchException.class,
		ServletRequestBindingException.class,
		BindException.class,
		MethodArgumentNotValidException.class,
		HttpRequestMethodNotSupportedException.class,
		HttpMediaTypeNotSupportedException.class,
		HttpMediaTypeNotAcceptableException.class,
		HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class,
		ConstraintViolationException.class
	})
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(FAIL_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PolicyViolationException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final PolicyViolationException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(ex.getCode(), ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ExternalIntegrationException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final ExternalIntegrationException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(ex.getCode(), ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RefreshTokenInvalidException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleRefreshTokenInvalidException(
			final RefreshTokenInvalidException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				"INVALID_REFRESH_TOKEN", REFRESH_TOKEN_INVALID_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ResourceNotFoundException.class, NoHandlerFoundException.class})
	public ApiResponse<ApiResponse.FailureBody> handleNotFound(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(FAIL_CODE, NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({AccessDeniedException.class})
	public ApiResponse<ApiResponse.FailureBody> handleForbidden(
			final AccessDeniedException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(FAIL_CODE, FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({AuthenticationException.class, MemberNotFoundException.class})
	public ApiResponse<ApiResponse.FailureBody> handle(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(FAIL_CODE, UNAUTHORIZED_MESSAGE, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({JsonParsingException.class})
	public ApiResponse<ApiResponse.FailureBody> handleJson(
			final JsonParsingException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(ex.getCode(), ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse<ApiResponse.FailureBody> handleInternalServerError(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				FAIL_CODE, SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
