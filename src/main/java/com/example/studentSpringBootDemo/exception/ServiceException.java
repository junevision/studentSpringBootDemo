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

    public ServiceException(CommonError commonError) {
        super(commonError.getMessage());
        this.code = commonError.getCode();
        this.message = commonError.getMessage();
    }
}
