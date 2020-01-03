package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.mappers.IngredientMapper;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientDao;
import com.aws.codestar.projecttemplates.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
//@CrossOrigin(origins = "http://amam-frontend.s3-website.eu-central-1.amazonaws.com")
@CrossOrigin(origins = "*")
public class IngredientsController {

    private IngredientService ingredientService;
    private IngredientDao ingredientDao;
    private IngredientMapper ingredientMapper;

    @Autowired
    public IngredientsController(IngredientService ingredientService, IngredientDao ingredientDao, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientDao = ingredientDao;
        this.ingredientMapper = ingredientMapper;
    }

    @GetMapping(path = "/ingredients")
    public @ResponseBody
    List<IngredientDTO> getAllIngredients() {
        return ingredientService.getAll();
    }

    @GetMapping(path = "/ingredients/{name}/{numberOfResults}")
    public @ResponseBody
    List<IngredientDTO> getIngredientsByPartialName(@PathVariable String name, @PathVariable int numberOfResults) {
        return ingredientService.getIngredientsByPartialName(name, numberOfResults);
    }

    @PostMapping(path = "/ingredient")
    public @ResponseBody
    IngredientDTO addNewMeal(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.create(ingredientDTO);
    }


}
