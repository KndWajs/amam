package com.aws.codestar.projecttemplates.controller;

import com.aws.codestar.projecttemplates.dao.IngredientRepository;
import com.aws.codestar.projecttemplates.dao.MealIngredientRepository;
import com.aws.codestar.projecttemplates.dao.MealRepository;
import com.aws.codestar.projecttemplates.dao.MenuRepository;
import com.aws.codestar.projecttemplates.entities.Ingredient;
import com.aws.codestar.projecttemplates.entities.Meal;
import com.aws.codestar.projecttemplates.entities.MealIngredient;
import com.aws.codestar.projecttemplates.entities.Menu;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/api/")
public class HelloWorldController {
    private IngredientRepository ingredientRepository;
    private MealRepository mealRepository;
    private MenuRepository menuRepository;
    private MealIngredientRepository mealIngredientRepository;


    @Autowired
    public HelloWorldController(IngredientRepository ingredientRepository, MealRepository mealRepository, MenuRepository menuRepository, MealIngredientRepository mealIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
        this.menuRepository = menuRepository;
        this.mealIngredientRepository = mealIngredientRepository;
    }

    @GetMapping(path="/ingredients")
    public @ResponseBody Iterable<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping(path="/meals")
    public @ResponseBody Iterable<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @GetMapping(path="/menu")
    public @ResponseBody Iterable<Menu> getMenu() {
        return menuRepository.findAll();
    }

    @GetMapping(path="/meals-ingredients")
    public @ResponseBody Iterable<MealIngredient> getMealsIngredients() {
        return mealIngredientRepository.findAll();
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