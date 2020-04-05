package pl.fit_amam.api.controllers;


import pl.fit_amam.api.Globals;
import pl.fit_amam.api.dto.IngredientCategoryDTO;
import pl.fit_amam.api.services.IngredientCategoryService;
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

    @PostMapping(path = "/ingredients/categories/")
    public @ResponseBody
    IngredientCategoryDTO addNewIngredient(@RequestBody IngredientCategoryDTO ingredientCategoryDTO) {
        return ingredientCategoryService.create(ingredientCategoryDTO);
    }

    @GetMapping(path = "/ingredients/categories/")
    public @ResponseBody
    List<IngredientCategoryDTO> getAllIngredientCategories() {
        return ingredientCategoryService.getAll();
    }
}
