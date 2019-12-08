package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dao.MealIngredientRepository;
import com.aws.codestar.projecttemplates.dto.IngredientDto;
import com.aws.codestar.projecttemplates.entities.Ingredient;
import com.aws.codestar.projecttemplates.entities.MealIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper implements Mapper<IngredientDto, Ingredient> {

    private MealIngredientRepository mealIngredientRepository;

    @Autowired
    public IngredientMapper(MealIngredientRepository mealIngredientRepository) {
        this.mealIngredientRepository = mealIngredientRepository;
    }

    @Override
    public IngredientDto toDto(Ingredient ingredient) {
        return IngredientDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .ingredientUnit(ingredient.getIngredientUnit())
                .mealIngredientsId(findMealIngredientsId(ingredient.getMealIngredients()))
                .build();
    }

    @Override
    public Ingredient toEntity(IngredientDto ingredientDto) {
        return Ingredient.builder()
                .id(ingredientDto.getId())
                .name(ingredientDto.getName())
                .ingredientUnit(ingredientDto.getIngredientUnit())
                .mealIngredients(findMealIngredientsFromId(ingredientDto.getMealIngredientsId()))
                .build();
    }

    private List<Long> findMealIngredientsId(List<MealIngredient> mealIngredients) {
        return mealIngredients.stream().map(a -> a.getId()).collect(Collectors.toList());
    }

    private List<MealIngredient> findMealIngredientsFromId(List<Long> mealsId) {
        return mealsId.stream().map(id -> mealIngredientRepository.findById(id.intValue()).get())
                .collect(Collectors.toList());
    }
}
