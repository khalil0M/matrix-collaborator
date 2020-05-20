package com.humanup.matrixcollaborator.aop.dto;

public class ProjectException extends HttpException {

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Project";
    }

}
