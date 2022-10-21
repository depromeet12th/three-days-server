package com.depromeet.threedays.front.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    RESOURCE_NOT_FOUND(404, "E001", "Resource not exists"),
    ;

    private final int status;
    private final String code;
    private final String message;
}
