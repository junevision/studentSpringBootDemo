package com.example.studentSpringBootDemo.exception;

import lombok.Getter;

/**
 * @author jun.lei
 * @created 01/02/2025 - 21:29
 * @description
 */
@Getter
public class ServiceException extends RuntimeException {

    private final int code;
    private final String message;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
