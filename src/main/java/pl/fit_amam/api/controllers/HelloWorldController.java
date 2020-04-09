package pl.fit_amam.api.controllers;

import com.nimbusds.jwt.JWTClaimsSet;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
@Tag(name = "HelloWorldController", description = "this is HelloWorldController")
public class HelloWorldController {

    private MealIngredientDao mealIngredientDao;

    @Autowired
    public HelloWorldController(MealIngredientDao mealIngredientDao) {
        this.mealIngredientDao = mealIngredientDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity helloWorldGet() throws JSONException {
        return ResponseEntity.ok(createResponse("This API version: " + Globals.API_VERSION));
    }

    private String createResponse(String text) throws JSONException {
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