package com.aws.codestar.projecttemplates.exceptions;

public class ObjectIsNullException extends RuntimeException {
    public ObjectIsNullException(String objectType) {
        super(objectType + " object is null!");
    }
}
