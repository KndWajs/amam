package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.persistence.repositories.MealRepository;
import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.mappers.MealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
//@CrossOrigin(origins = "http://amam-frontend.s3-website.eu-central-1.amazonaws.com")
@CrossOrigin(origins = "*")
public class MealsController {

    private MealRepository mealRepository;
    private MealMapper mealMapper;

    @Autowired
    public MealsController(MealRepository mealRepository, MealMapper mealMapper) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
    }

    @GetMapping(path = "/meals/")
    public @ResponseBody
    List<MealDTO> getAllMeals() {
        List<MealDTO> meals = new ArrayList<>();
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
    MealDTO getMealById(@PathParam("id") Long id) {
        return mealMapper.toDto(mealRepository.findById(id).get()); //TODO redirect to service
    }

    @PostMapping(path = "/meal")
    public @ResponseBody
    MealDTO addNewMeal(@RequestBody MealDTO mealdto) {
        return mealMapper.toDto(mealRepository.save(mealMapper.toEntity(mealdto)));
    }

    @PutMapping(path = "/meal/{id}")
    public @ResponseBody
    MealDTO updateMeal(@RequestBody MealDTO mealdto, @PathVariable Long id) {
        return null; //TODO implement
    }

    @DeleteMapping(path = "/meal/{id}")
    public @ResponseBody
    MealDTO deleteMeal(@PathVariable Long id) {
        MealDTO deletedMeal = mealMapper.toDto(mealRepository.findById(id).get());
        mealRepository.deleteById(id);
        return deletedMeal; //TODO check if it is deleted?
    }

}
