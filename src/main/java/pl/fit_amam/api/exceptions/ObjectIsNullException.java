package pl.fit_amam.api.exceptions;

public class ObjectIsNullException extends RuntimeException {
    public ObjectIsNullException(String objectType) {
        super(objectType + " object is null!");
    }
}
