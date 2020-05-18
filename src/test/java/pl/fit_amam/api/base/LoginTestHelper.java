package pl.fit_amam.api.base;


import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.fit_amam.api.security.cognitojwt.CognitoAuthenticationToken;

public final class LoginTestHelper {

    public static void loginAsRegularUser() {
        Authentication authentication = new CognitoAuthenticationToken(null,
                new JWTClaimsSet.Builder().claim("email", "email4test@test.com").build());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

