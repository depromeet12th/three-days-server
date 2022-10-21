package com.depromeet.threedays.front.controller;

import com.depromeet.threedays.front.exception.PolicyViolationException;
import com.depromeet.threedays.front.exception.ResourceNotFoundException;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponse.FailureBody;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import com.depromeet.threedays.front.support.FailureBodyResolver;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


@Slf4j
@RestControllerAdvice
public class ApiControllerExceptionHandler {

    private static final String LOG_MESSAGE_FORMAT = "{} '{}' - {}";

    private static final String UNCAUGHT_LOG_MESSAGE = "??";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public final ApiResponse<Void> handleBadRequest(final IllegalArgumentException ex, final WebRequest request){
        this.writeLog(ex, request);
        return ApiResponseGenerator.fail();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PolicyViolationException.class)
    public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(final PolicyViolationException ex, final WebRequest request){
        this.writeLog(ex, request);
        return ApiResponseGenerator.fail(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(final MethodArgumentTypeMismatchException ex, final WebRequest request){
        this.writeLog(ex, request);
        return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(final MissingServletRequestParameterException ex, final WebRequest request){
        this.writeLog(ex, request);
        return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(final ConstraintViolationException ex, final WebRequest request){
        this.writeLog(ex, request);
        return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BindException.class, MethodArgumentNotValidException.class})
    public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(final BindException ex, final WebRequest request){
        this.writeLog(ex, request);
        return ApiResponseGenerator.fail(FailureBodyResolver.resolveFrom(ex));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class, NoHandlerFoundException.class})
    public final ApiResponse<Void> handleNotFoundException(final Exception ex, final WebRequest request) {
        this.writeLog(ex, request);
        return ApiResponseGenerator.fail();
    }

    // TODO: FORBIDDEN, UNAUTHORIZED

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleInternalServerError(final Exception ex, final WebRequest request){
        this.writeLog(ex, request);
        return ApiResponseGenerator.fail();
    }

    private <E extends Exception> void writeLog(final E ex, final WebRequest webRequest) {
        try{
            HttpServletRequest servletRequest = ((ServletWebRequest) webRequest).getRequest();
            log.error(
                    LOG_MESSAGE_FORMAT,
                    servletRequest.getMethod(),
                    servletRequest.getRequestURI(),
                    ex.getMessage(),
                    ex
            );
        }catch (Exception e){
            log.error(LOG_MESSAGE_FORMAT, UNCAUGHT_LOG_MESSAGE, UNCAUGHT_LOG_MESSAGE, e.getMessage(), e);
        }
    }
}
