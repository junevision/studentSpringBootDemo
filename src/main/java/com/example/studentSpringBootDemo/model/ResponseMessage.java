package com.example.studentSpringBootDemo.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author jun.lei
 * @created 09/01/2025 - 11:55
 * @description
 */
@Data
public class ResponseMessage<T> {

    private int code;
    private String message;
    private T data;

    public ResponseMessage() {
    }

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseMessage success() {
        return new ResponseMessage(HttpStatus.OK.value(), "success", null);
    }

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", data);
    }
}
