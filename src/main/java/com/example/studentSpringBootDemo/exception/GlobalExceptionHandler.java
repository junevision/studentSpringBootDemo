package com.example.studentSpringBootDemo.exception;

import com.example.studentSpringBootDemo.common.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author jun.lei
 * @created 09/01/2025 - 12:26
 * @description
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseMessage exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception: {}", e.getMessage());
        return new ResponseMessage<>(500, "error", null);
    }

    @ExceptionHandler(value = ServiceException.class)
    public ResponseMessage serviceExceptionHandler(ServiceException e) {
        log.error("Service Exception: {}", e.getMessage());
        return new ResponseMessage<>(e.getCode(), e.getMessage(), null);
    }
}
