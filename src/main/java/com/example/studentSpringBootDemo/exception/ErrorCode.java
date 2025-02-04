package com.example.studentSpringBootDemo.exception;

import lombok.Getter;

/**
 * @author jun.lei
 * @created 02/02/2025 - 12:44
 * @description
 */
@Getter
public enum ErrorCode {

    STUDENT_NOT_EXISTS(20001, "Student not exists"),
    STUDENT_EMAIL_ALREADY_EXISTS(20002, "Student email already exists"),
    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
