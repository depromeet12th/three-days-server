package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.exception.JsonParsingException;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ApiControllerExceptionHandler {

	private static final String LOG_MESSAGE_FORMAT = "{} '{}' - {}";
	private static final String UNCAUGHT_LOG_MESSAGE = "??";

	private static final String BAD_REQUEST_CODE = "400";
	private static final String BAD_REQUEST_MESSAGE = "잘못된 요청입니다.";

	private static final String FORBIDDEN_CODE = "403";
	private static final String FORBIDDEN_MESSAGE = "접근 권한이 없습니다.";

	private static final String NOT_FOUND_CODE = "404";
	private static final String NOT_FOUND_MESSAGE = "요청과 일치하는 결과를 찾을 수 없습니다.";

	private static final String SERVER_ERROR_CODE = "500";
	private static final String SERVER_ERROR_MESSAGE = "알 수 없는 오류가 발생하였습니다.";

	@ExceptionHandler(IllegalArgumentException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final IllegalArgumentException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PolicyViolationException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final PolicyViolationException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({MethodArgumentTypeMismatchException.class, TypeMismatchException.class})
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final TypeMismatchException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final ServletRequestBindingException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final BindException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final ConstraintViolationException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final HttpRequestMethodNotSupportedException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final HttpMediaTypeNotSupportedException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final HttpMediaTypeNotAcceptableException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final HttpMessageNotReadableException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	public ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final MissingServletRequestPartException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ResourceNotFoundException.class, NoHandlerFoundException.class})
	public ApiResponse<ApiResponse.FailureBody> handleNotFound(
			final Exception ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(NOT_FOUND_CODE, NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({AccessDeniedException.class})
	public ApiResponse<ApiResponse.FailureBody> handleForbidden(
			final AccessDeniedException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FORBIDDEN_CODE, FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({JsonParsingException.class})
	public ApiResponse<ApiResponse.FailureBody> handleJson(
			final JsonParsingException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(FORBIDDEN_CODE, FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse<ApiResponse.FailureBody> handleInternalServerError(
			final Exception ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				SERVER_ERROR_CODE, SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
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
