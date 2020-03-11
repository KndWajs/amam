package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.Globals;
import com.aws.codestar.projecttemplates.dto.IngredientCategoryDTO;
import com.aws.codestar.projecttemplates.services.IngredientCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
@Tag(name = "IngredientCategoryController", description = "desc class")
public class IngredientCategoryController {

    private IngredientCategoryService ingredientCategoryService;

    @Autowired
    public IngredientCategoryController(IngredientCategoryService ingredientCategoryService) {
        this.ingredientCategoryService = ingredientCategoryService;
    }

    @GetMapping(path = "/ingredients/categories/")
    public @ResponseBody
    List<IngredientCategoryDTO> getAllIngredientCategories() {
        return ingredientCategoryService.getAllIngredientCategories();
    }

    @PostMapping(path = "/ingredients/categories/")
    public @ResponseBody
    IngredientCategoryDTO addNewIngredient(@RequestBody IngredientCategoryDTO ingredientCategoryDTO) {
        return ingredientCategoryService.addNewIngredientCategory(ingredientCategoryDTO);
    }

}
