package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.Globals;
import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.services.MealService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
@Tag(name = "MealsController", description = "desc class")
public class MealsController {

    private MealService mealService;

    @Autowired
    public MealsController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(path = "/meals/")
    public @ResponseBody
    List<MealDTO> getAllMeals() {
        return mealService.getAll();
    }

//    @GetMapping(path = "/meal")
//    public @ResponseBody
//    MealDto getMealByName(@PathParam("name") String name) {
//        return null;
//    }

    @GetMapping(path = "/meal")
    public @ResponseBody
    MealDTO getMealById(@PathParam("id") Long id) {
        return mealService.get(id);
    }

    @GetMapping(path = "/meals/{name}/{numberOfResults}")
    public @ResponseBody
    List<MealDTO> getMealsByPartialName(@PathVariable String name, @PathVariable int numberOfResults) {
        return mealService.getMealsByPartialName(name, numberOfResults);
    }

    @PostMapping(path = "/meal")
    public @ResponseBody
    MealDTO addNewMeal(@RequestBody MealDTO mealdto) {
        return mealService.create(mealdto);
    }

    @PutMapping(path = "/meal/{id}")
    public @ResponseBody
    MealDTO updateMeal(@RequestBody MealDTO mealdto, @PathVariable Long id) {
        return null; //TODO implement
    }

    @DeleteMapping(path = "/meal/{id}")
    public @ResponseBody
    MealDTO deleteMeal(@PathVariable Long id) {
        MealDTO deletedMeal = mealService.get(id);
        mealService.delete(id);
        return deletedMeal; //TODO check if it is deleted?
    }

}
