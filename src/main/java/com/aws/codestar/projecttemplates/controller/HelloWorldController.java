package com.aws.codestar.projecttemplates.controller;

import com.aws.codestar.projecttemplates.Ingredient;
import com.aws.codestar.projecttemplates.IngredientRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {
    private IngredientRepository ingredientRepository;


    @Autowired
    public HelloWorldController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @GetMapping(path="/ingredients")
    public @ResponseBody Iterable<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
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