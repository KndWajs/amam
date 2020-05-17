package pl.fit_amam.api.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.security.cognitojwt.CognitoAuthenticationToken;

@Service
@Transactional
public class UserService {


    public static String getUserName (){
        CognitoAuthenticationToken ct = (CognitoAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return ct.getPrincipal().getClaim("email").toString();
    }
}
