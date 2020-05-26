package pl.fit_amam.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ObjectIdDoesNotExistsException extends ResponseStatusException {

    public ObjectIdDoesNotExistsException() {
        super(HttpStatus.NOT_FOUND, "Object id does not exists!");
    }

    public ObjectIdDoesNotExistsException(Long id) {
        super(HttpStatus.NOT_FOUND, "Object id = " + id + " does not exists! ");
    }

    public ObjectIdDoesNotExistsException(Long id, String objectType) {
        super(HttpStatus.NOT_FOUND, "Object id = " + id + " for " + objectType + " does not exists! ");
    }
}
