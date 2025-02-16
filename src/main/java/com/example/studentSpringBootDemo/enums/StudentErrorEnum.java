package com.example.studentSpringBootDemo.enums;

import com.example.studentSpringBootDemo.exception.CommonError;
import lombok.Getter;

/**
 * @author jun.lei
 * @created 02/02/2025 - 12:44
 * @description
 */
@Getter
public enum StudentErrorEnum implements CommonError {

    STUDENT_NOT_EXISTS(10001, "Student not exists"),
    STUDENT_EMAIL_ALREADY_EXISTS(10002, "Student email already exists"),
    PARAMETER_ERROR(10003, "Parameter error");

    private final int code;
    private final String message;

    StudentErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
