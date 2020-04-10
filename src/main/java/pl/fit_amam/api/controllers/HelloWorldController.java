package pl.fit_amam.api.controllers;

import com.nimbusds.jwt.JWTClaimsSet;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.fit_amam.api.Globals;
import pl.fit_amam.api.persistence.repositories.MealIngredientDao;
import pl.fit_amam.api.security.cognitojwt.AwsJwtValidator;
import pl.fit_amam.api.security.cognitojwt.CognitoAuthenticationToken;
import pl.fit_amam.api.security.cognitojwt.TokenException;

import java.io.IOException;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
@Tag(name = "HelloWorldController", description = "this is HelloWorldController")
public class HelloWorldController {

    private MealIngredientDao mealIngredientDao;

    @Value("${version}")
    private String apiVersion;

    @Autowired
    public HelloWorldController(MealIngredientDao mealIngredientDao) {
        this.mealIngredientDao = mealIngredientDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity helloWorldGet() throws JSONException {


        List<String> response = new ArrayList<>();
        response.add("API version: " + apiVersion);
        response.add("Rest version: " + Globals.API_VERSION);

        return ResponseEntity.ok(createResponse(response));
    }

    private String createResponse(List<String> text) throws JSONException {
        return new JSONObject().put("Output", text).toString();
    }


    //TODO for testing purpose - remove this later
    @GetMapping(path = "/get-user-info/")
    @ResponseBody
    public String test(@RequestHeader("Authorization") String token) throws IOException, TokenException {

        JWTClaimsSet clset = AwsJwtValidator.validateAWSJwtToken(token);

        String claims = "";
        for (Map.Entry<String, Object> claim :clset.getClaims().entrySet()) {
            claims = claims + "\n" + claim.getKey() + ": " + claim.getValue().toString() ;
        }

        CognitoAuthenticationToken ct = (CognitoAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        return "User data taken from token: \n"
                + claims + "\n"
                + ct.getPrincipal().getClaim("email").toString() + "\n"
                + ct.getPrincipal().getExpirationTime() + "\n"
                ;
    }
}