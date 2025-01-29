package com.example.studentSpringBootDemo.model;

import org.springframework.http.HttpStatus;

/**
 * @author jun.lei
 * @created 09/01/2025 - 11:55
 * @description
 */
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseMessage() {
    }

    public ResponseMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResponseMessage success() {
        return new ResponseMessage(HttpStatus.OK.value(), "success", null);
    }

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", data);
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
