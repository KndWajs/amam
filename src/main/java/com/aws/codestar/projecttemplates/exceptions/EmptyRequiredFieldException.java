package com.aws.codestar.projecttemplates.exceptions;

public class EmptyRequiredFieldException extends RuntimeException {
    public EmptyRequiredFieldException(String field) {
        super(field + " can not be empty!");
    }
}
