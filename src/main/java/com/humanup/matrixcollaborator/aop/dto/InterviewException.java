package com.humanup.matrixcollaborator.aop.dto;

public class InterviewException extends HttpException {

    public InterviewException(String message) {
        super(message);
    }

    public InterviewException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Interview";
    }

}
