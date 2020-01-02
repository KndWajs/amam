package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.persistence.repositories.IngredientDao;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientRepository;
import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.mappers.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
//@CrossOrigin(origins = "http://amam-frontend.s3-website.eu-central-1.amazonaws.com")
@CrossOrigin(origins = "*")
public class IngredientsController {

    private IngredientRepository ingredientRepository;
    private IngredientDao ingredientDao;
    private IngredientMapper ingredientMapper;

    @Autowired
    public IngredientsController(IngredientRepository ingredientRepository, IngredientDao ingredientDao, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientDao = ingredientDao;
        this.ingredientMapper = ingredientMapper;
    }

    @GetMapping(path = "/ingredients/")
    public @ResponseBody
    List<IngredientDTO> getAllIngredients() {
        List<IngredientDTO> ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientRepository.findAll()) {
            ingredients.add(ingredientMapper.toDto(ingredient));
        }
        return ingredients;
    }

    @GetMapping(path = "/ingredients/{name}/{numberOfResults}")
    public @ResponseBody
    List<IngredientDTO> getIngredientsByPartialName(@PathVariable String name, @PathVariable int numberOfResults) {
        List<IngredientDTO> ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientDao.getIngredientsByPartialName(name.toLowerCase(), numberOfResults)) {
            ingredients.add(ingredientMapper.toDto(ingredient));
        }
        return ingredients;
    }


}
