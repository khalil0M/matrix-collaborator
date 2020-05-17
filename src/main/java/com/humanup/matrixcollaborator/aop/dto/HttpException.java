package com.humanup.matrixcollaborator.aop.dto;

public class HttpException extends Exception {

    public HttpException(String message) {
        super(message);
    }

    public HttpException() {
        super();
    }
}
