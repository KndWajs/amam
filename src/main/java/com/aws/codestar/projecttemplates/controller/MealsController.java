package com.aws.codestar.projecttemplates.controller;


import com.aws.codestar.projecttemplates.dao.MealRepository;
import com.aws.codestar.projecttemplates.dto.MealDto;
import com.aws.codestar.projecttemplates.entities.Meal;
import com.aws.codestar.projecttemplates.mappers.MealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class MealsController {

    private MealRepository mealRepository;
    private MealMapper mealMapper;

    @Autowired
    public MealsController(MealRepository mealRepository, MealMapper mealMapper) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/meals/")
    public @ResponseBody
    List<MealDto> getAllMeals() {
        List<MealDto> meals = new ArrayList<>();
        for (Meal meal : mealRepository.findAll()) {
            meals.add(mealMapper.toDto(meal));
        }
        return meals;
    }

//    @GetMapping(path = "/meal")
//    public @ResponseBody
//    MealDto getMealByName(@PathParam("name") String name) {
//        return null;
//    }

    @GetMapping(path = "/meal")
    public @ResponseBody
    MealDto getMealById(@PathParam("id") Long id) {
        return mealMapper.toDto(mealRepository.findById(id).get()); //TODO redirect to service
    }

    @PostMapping(path = "/meal")
    public @ResponseBody
    MealDto addNewMeal(@RequestBody MealDto mealdto) {
        return null; //TODO implement
    }

    @PutMapping(path = "/meal/{id}")
    public @ResponseBody
    MealDto updateMeal(@RequestBody MealDto mealdto, @PathVariable Long id) {
        return null; //TODO implement
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/meal/{id}")
    public @ResponseBody
    MealDto deleteMeal(@PathVariable Long id) {
        MealDto deletedMeal = mealMapper.toDto(mealRepository.findById(id).get());
        mealRepository.deleteById(id);
        return deletedMeal; //TODO check if it is deleted?
    }

}
