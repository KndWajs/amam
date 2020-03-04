package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper implements Mapper<IngredientDTO, Ingredient> {

    private MealIngredientDao mealIngredientDao;

    @Autowired
    public IngredientMapper(MealIngredientDao mealIngredientDao) {
        this.mealIngredientDao = mealIngredientDao;
    }

    @Override
    public IngredientDTO toDTO(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        return IngredientDTO.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .ingredientUnit(ingredient.getIngredientUnit())
                .category(ingredient.getCategory())
                .build();
    }

    @Override
    public Ingredient toEntity(IngredientDTO ingredient) {
        if (ingredient == null) {
            return null;
        }

        return Ingredient.builder()
                .id(ingredient.getId())
                .name(ingredient.getName() == null ? null : ingredient.getName().toLowerCase())
                .ingredientUnit(ingredient.getIngredientUnit())
                .mealIngredients(ingredient.getId() == null ? null : mealIngredientDao.getMealIngredientsByIngredientId(ingredient.getId()))
                .category(ingredient.getCategory())
                .build();
    }
}
