package pl.fit_amam.api.security.cognitojwt;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CognitoAuthenticationToken extends AbstractAuthenticationToken {
    private String token;
    private JWTClaimsSet details;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public CognitoAuthenticationToken(String token, JWTClaimsSet details) {
        super(new ArrayList<>());
        this.token = token;
        this.details = details;
        this.setAuthenticated(true);

        //todo refactor this
        List<String> auth = ((List<String>) details.getClaim("cognito:groups"));
        if(auth ==null || auth.isEmpty()){
            authorities.add(new CognitoAuthorities("NORMAL_USER"));
        } else {
            authorities.add(new CognitoAuthorities(auth.get(0)));
        }
    }

    @Override
    public String getCredentials() {
        return token;
    }

    @Override
    public JWTClaimsSet getPrincipal() {
        return details;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

}

