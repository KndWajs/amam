package pl.fit_amam.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TokenException extends ResponseStatusException {

    public TokenException(HttpStatus httpStatus, String errorReason) {
        super(httpStatus, errorReason);
    }

    public TokenException(HttpStatus httpStatus, String errorReason, Throwable cause) {
        super(httpStatus, errorReason, cause);
    }
}
