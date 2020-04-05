package pl.fit_amam.api.exceptions;

public class EmptyRequiredFieldException extends RuntimeException {
    public EmptyRequiredFieldException(String field) {
        super(field + " can not be empty!");
    }
}
