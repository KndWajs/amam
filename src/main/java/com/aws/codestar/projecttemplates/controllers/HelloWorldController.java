package com.aws.codestar.projecttemplates.controllers;

import com.aws.codestar.projecttemplates.Globals;
import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientDao;
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

    private static final String MESSAGE_FORMAT = "Hello %s!";

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity helloWorldGet(@RequestParam(value = "name", defaultValue = "World") String name) throws JSONException {
        return ResponseEntity.ok(createResponse(name));
    }

    private String createResponse(String name) throws JSONException {
        return new JSONObject().put("Output", String.format(MESSAGE_FORMAT, name)).toString();
    }

}