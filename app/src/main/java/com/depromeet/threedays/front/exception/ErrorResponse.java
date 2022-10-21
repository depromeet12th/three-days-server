package com.depromeet.threedays.front.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter

public class ErrorResponse {
    private String message;
    private int status;
    private String code;

    public ErrorResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public static ErrorResponse of(ErrorCode resourceNotFound) {
        return new ErrorResponse(resourceNotFound.getMessage(), resourceNotFound.getStatus(), resourceNotFound.getCode());
    }

}
