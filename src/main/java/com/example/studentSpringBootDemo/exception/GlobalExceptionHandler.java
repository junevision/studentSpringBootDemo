package com.example.studentSpringBootDemo.exception;

import com.example.studentSpringBootDemo.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author jun.lei
 * @created 09/01/2025 - 12:26
 * @description
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseMessage handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception: {}", e.getMessage());
        return new ResponseMessage<>(500, "error", null);
    }
}
