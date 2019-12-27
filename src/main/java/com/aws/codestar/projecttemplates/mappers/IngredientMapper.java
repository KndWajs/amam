package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dao.MealIngredientDao;
import com.aws.codestar.projecttemplates.dao.MealIngredientRepository;
import com.aws.codestar.projecttemplates.dto.IngredientDto;
import com.aws.codestar.projecttemplates.entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper implements Mapper<IngredientDto, Ingredient> {

    private MealIngredientRepository mealIngredientRepository;
    private MealIngredientDao mealIngredientDao;

    @Autowired
    public IngredientMapper(MealIngredientRepository mealIngredientRepository, MealIngredientDao mealIngredientDao) {
        this.mealIngredientRepository = mealIngredientRepository;
        this.mealIngredientDao = mealIngredientDao;
    }

    @Override
    public IngredientDto toDto(Ingredient ingredient) {
        return IngredientDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .ingredientUnit(ingredient.getIngredientUnit())
                .build();
    }

    @Override
    public Ingredient toEntity(IngredientDto ingredientDto) {
        return Ingredient.builder()
                .id(ingredientDto.getId())
                .name(ingredientDto.getName())
                .ingredientUnit(ingredientDto.getIngredientUnit())
                .mealIngredients(mealIngredientDao.getMealIngredientsByIngredientId(ingredientDto.getId()))
                .build();
    }
}
