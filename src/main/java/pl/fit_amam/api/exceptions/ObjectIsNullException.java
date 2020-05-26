package pl.fit_amam.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ObjectIsNullException extends ResponseStatusException {

    public ObjectIsNullException(String objectType) {
        super(HttpStatus.NOT_FOUND, objectType + " object is null!");
    }
}
