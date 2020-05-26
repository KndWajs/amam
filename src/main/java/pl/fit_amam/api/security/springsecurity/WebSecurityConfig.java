package pl.fit_amam.api.security.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST).not().hasAuthority("guest")
                .antMatchers(HttpMethod.PUT, "/**").not().hasAuthority("guest")
                .antMatchers(HttpMethod.DELETE, "/**").not().hasAuthority("guest")
                .and()
                .addFilter(new AuthFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security", "/swagger-ui.html",
                "/webjars/**", "/swagger-resources/configuration/ui",
                "/swagge‌r-ui.html", "/docs/**",
                "/swagger-resources/configuration/security"
        );
    }
}