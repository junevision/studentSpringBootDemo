package com.example.studentSpringBootDemo.exception;

import com.example.studentSpringBootDemo.common.ResponseMessage;
import com.example.studentSpringBootDemo.enums.StudentErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;


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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseMessage validationExceptionHandler(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(","));
        log.error("Validation Exception: {}", errorMessage);
        return new ResponseMessage<>(StudentErrorEnum.PARAMETER_ERROR.getCode(), errorMessage, null);
    }
}
