package com.example.studentSpringBootDemo.exception;

import lombok.Data;

/**
 * @author jun.lei
 * @created 01/02/2025 - 21:29
 * @description
 */
@Data
public class ServiceException extends RuntimeException{

    private final int code;
    private final String message;

    public ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
