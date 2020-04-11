package pl.fit_amam.api.security.cognitojwt;

import org.springframework.security.core.GrantedAuthority;

public class CognitoAuthorities implements GrantedAuthority {
    String grantedAuthority;

    public CognitoAuthorities (String grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

    @Override public String getAuthority() {
        return this.grantedAuthority;
    }
}
