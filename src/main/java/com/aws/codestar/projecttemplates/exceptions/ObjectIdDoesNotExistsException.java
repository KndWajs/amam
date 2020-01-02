package com.aws.codestar.projecttemplates.exceptions;

public class ObjectIdDoesNotExistsException extends RuntimeException {
    public ObjectIdDoesNotExistsException() {
        super("Object id does not exists!");
    }
    public ObjectIdDoesNotExistsException(Long id) {
        super("Object id = " + id + "does not exists! ");
    }
}
