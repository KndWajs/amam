package pl.fit_amam.api.security.cognitojwt;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;

public class TokenException extends ServletException {

    //TODO can it be servletException?

    private HttpStatus httpStatus;

    public TokenException(HttpStatus httpStatus, String errorReason, String message) {
        super(errorReason + message);
    }
}
