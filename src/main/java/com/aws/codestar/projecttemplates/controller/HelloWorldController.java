package com.aws.codestar.projecttemplates.controller;

import com.aws.codestar.projecttemplates.dao.IngredientRepository;
import com.aws.codestar.projecttemplates.dao.MealIngredientRepository;
import com.aws.codestar.projecttemplates.dao.MealRepository;
import com.aws.codestar.projecttemplates.dao.MenuRepository;
import com.aws.codestar.projecttemplates.mappers.MealMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/v1/api/")
public class HelloWorldController {
    private IngredientRepository ingredientRepository;
    private MealRepository mealRepository;
    private MealMapper mealMapper;
    private MenuRepository menuRepository;
    private MealIngredientRepository mealIngredientRepository;

    @Autowired
    public HelloWorldController(IngredientRepository ingredientRepository, MealRepository mealRepository, MenuRepository menuRepository, MealIngredientRepository mealIngredientRepository, MealMapper mealMapper) {
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
        this.menuRepository = menuRepository;
        this.mealIngredientRepository = mealIngredientRepository;
        this.mealMapper = mealMapper;
    }

    private static final String MESSAGE_FORMAT = "Hello %s!";

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity helloWorldGet(@RequestParam(value = "name", defaultValue = "World") String name) throws JSONException {
        return ResponseEntity.ok(createResponse(name));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity helloWorldPost(@RequestParam(value = "name", defaultValue = "World") String name) throws JSONException {
        return ResponseEntity.ok(createResponse(name));
    }

    private String createResponse(String name) throws JSONException {
        return new JSONObject().put("Output", String.format(MESSAGE_FORMAT, name)).toString();
    }
}