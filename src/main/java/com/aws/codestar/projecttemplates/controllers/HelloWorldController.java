package com.aws.codestar.projecttemplates.controllers;

import com.aws.codestar.projecttemplates.Globals;
import com.aws.codestar.projecttemplates.Tth;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientDao;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public HelloWorldController(MealIngredientDao mealIngredientDao) {
        this.mealIngredientDao = mealIngredientDao;
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

List<Tth> threads = new ArrayList<Tth>();

    @GetMapping(path = "/test/")
    public ResponseEntity startNewThread(@RequestParam(value = "name", defaultValue = "World") String name) throws JSONException {

        Tth tth = new Tth();
        threads.add(tth);
        Thread ttth = new Thread(tth);
        ttth.start();

        return ResponseEntity.ok(createResponse(Integer.toString((threads.indexOf(tth)))));
    }

    @GetMapping(path = "/test2/")
    public ResponseEntity checkThread(@RequestParam(value = "name", defaultValue = "World") int threadInt) throws JSONException {
        return ResponseEntity.ok(createResponse(Integer.toString(threads.get(threadInt).getSomeInt())));
    }

    @GetMapping(path = "/test3/")
    public ResponseEntity checkException(@RequestParam(value = "name", defaultValue = "World") int threadInt) throws JSONException {
        if(threadInt == 3){
            throw new IllegalArgumentException("this is wrong argument");
        }
        return ResponseEntity.ok(new Meal());
    }

    private String createResponse(String name) throws JSONException {
        return new JSONObject().put("Output", String.format(MESSAGE_FORMAT, name)).toString();
    }

}