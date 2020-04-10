package pl.fit_amam.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.fit_amam.api.Globals;
import pl.fit_amam.api.persistence.repositories.MealIngredientDao;

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
    private String fb;

    @Autowired
    public HelloWorldController(MealIngredientDao mealIngredientDao) {
        this.mealIngredientDao = mealIngredientDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity helloWorldGet() throws JSONException {


        List<String> response = new ArrayList<>();
        response.add("API version: " + fb);
        response.add("Rest version: " + Globals.API_VERSION);

        return ResponseEntity.ok(createResponse(response));
    }

    private String createResponse(List<String> text) throws JSONException {
        return new JSONObject().put("Output", text).toString();
    }

}