package pl.fit_amam.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.fit_amam.api.Globals;
import pl.fit_amam.api.dto.MealDTO;
import pl.fit_amam.api.services.MealService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
public class MealsController {

    private MealService mealService;

    @Autowired
    public MealsController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping(path = "/meal")
    public @ResponseBody
    MealDTO addNewMeal(@RequestBody MealDTO mealdto) {
        return mealService.create(mealdto);
    }

    @GetMapping(path = "/meals/")
    public @ResponseBody
    List<MealDTO> getAllMeals() {
        return mealService.getAll();
    }

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

    @PutMapping(path = "/meal/{id}")
    public @ResponseBody
    MealDTO updateMeal(@RequestBody MealDTO mealDto) {
        return mealService.update(mealDto);
    }

    @DeleteMapping(path = "/meal/{id}")
    public @ResponseBody
    MealDTO deleteMeal(@PathVariable Long id) {
        MealDTO deletedMeal = mealService.get(id);
        mealService.delete(id);
        return deletedMeal;
    }

}
