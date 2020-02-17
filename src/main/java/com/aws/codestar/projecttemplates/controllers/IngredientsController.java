package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.Globals;
import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.mappers.IngredientMapper;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientDao;
import com.aws.codestar.projecttemplates.services.IngredientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
@Tag(name = "IngredientsController", description = "desc class")
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
    IngredientDTO addNewIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.create(ingredientDTO);
    }

    @PutMapping(path = "/ingredient/{id}")
    public @ResponseBody
    IngredientDTO updateIngredient(@RequestBody IngredientDTO ingredientDTO, @PathVariable Long id) {
        return ingredientService.update(ingredientDTO, id);
    }
}
