package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.persistence.entities.IngredientCategory;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/v1/api")
//@CrossOrigin(origins = "http://amam-frontend.s3-website.eu-central-1.amazonaws.com")
@CrossOrigin(origins = "*")
public class IngredientCategoryController {

    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    public IngredientCategoryController(IngredientCategoryRepository ingredientCategoryRepository) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
    }

    @GetMapping(path = "/ingredients/categories/")
    public @ResponseBody
    List<IngredientCategory> getAllIngredientCategories() {
        return StreamSupport
                .stream(ingredientCategoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/ingredients/categories/")
    public @ResponseBody
    IngredientCategory addNewIngredient(@RequestBody IngredientCategory ingredientCategory) {
        IngredientCategory newIngredientCategory = ingredientCategory;
        ingredientCategory.setCategory(ingredientCategory.getCategory().toLowerCase());
        return ingredientCategoryRepository.save(newIngredientCategory);
    }

}
