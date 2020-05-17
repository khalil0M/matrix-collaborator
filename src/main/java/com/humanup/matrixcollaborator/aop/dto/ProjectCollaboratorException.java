package com.humanup.matrixcollaborator.aop.dto;

public class ProjectCollaboratorException extends HttpException {

    public ProjectCollaboratorException(String message) {
        super(message);
    }

    public ProjectCollaboratorException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Project Collaborator";
    }

}
