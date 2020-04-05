package pl.fit_amam.api.controllers;

import pl.fit_amam.api.Globals;
import pl.fit_amam.api.persistence.repositories.MealIngredientDao;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}