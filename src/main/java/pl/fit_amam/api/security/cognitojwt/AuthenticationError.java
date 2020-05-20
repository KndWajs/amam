package pl.fit_amam.api.security.cognitojwt;

public class AuthenticationError {
    public static final String NOT_VALID_JSON_WEB_TOKEN = "Not a valid json web token";
    public static final String TOKEN_EXPIRED = "Jwt Token has expired.";
    public static final String TOKEN_MISSING = "Token missing";
}
