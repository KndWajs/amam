package pl.fit_amam.api.security.springsecurity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;
import pl.fit_amam.api.security.cognitojwt.AwsJwtValidator;
import pl.fit_amam.api.security.cognitojwt.CognitoAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends BasicAuthenticationFilter {
    public AuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final String token = request.getHeader("Authorization");

        try {
            CognitoAuthenticationToken cognitoAuthenticationToken =
                    new CognitoAuthenticationToken(token, AwsJwtValidator.validateAWSJwtToken(token));
            SecurityContextHolder.getContext().setAuthentication(cognitoAuthenticationToken);
            chain.doFilter(request, response);
        } catch (ResponseStatusException e) {
            response.sendError(e.getStatus().value(), e.getReason());
        }
    }
}
