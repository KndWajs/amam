package com.aws.codestar.projecttemplates.exceptions;

public class ObjectIdDoesNotExistsException extends RuntimeException {
    public ObjectIdDoesNotExistsException() {
        super("Object id does not exists!");
    }
    public ObjectIdDoesNotExistsException(Long id) {
        super("Object id = " + id + " does not exists! ");
    }

    public ObjectIdDoesNotExistsException(Long id, String objectType) {
        super("Object id = " + id + " for " + objectType + " does not exists! ");
    }
}
