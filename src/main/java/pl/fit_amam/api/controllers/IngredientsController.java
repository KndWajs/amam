package pl.fit_amam.api.controllers;


import pl.fit_amam.api.Globals;
import pl.fit_amam.api.dto.IngredientDTO;
import pl.fit_amam.api.services.IngredientService;
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

    @Autowired
    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping(path = "/ingredient")
    public @ResponseBody
    IngredientDTO addNewIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.create(ingredientDTO);
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

    @PutMapping(path = "/ingredient/{id}")
    public @ResponseBody
    IngredientDTO updateIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.update(ingredientDTO);
    }
}
