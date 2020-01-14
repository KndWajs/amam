package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientDao;
import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientRepository;
import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper implements Mapper<IngredientDTO, Ingredient> {

    private MealIngredientRepository mealIngredientRepository;
    private MealIngredientDao mealIngredientDao;

    @Autowired
    public IngredientMapper(MealIngredientRepository mealIngredientRepository, MealIngredientDao mealIngredientDao) {
        this.mealIngredientRepository = mealIngredientRepository;
        this.mealIngredientDao = mealIngredientDao;
    }

    @Override
    public IngredientDTO toDTO(Ingredient ingredient) {
        return IngredientDTO.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .ingredientUnit(ingredient.getIngredientUnit())
                .category(ingredient.getCategory())
                .build();
    }

    @Override
    public Ingredient toEntity(IngredientDTO ingredientDto) {
        return Ingredient.builder()
                .id(ingredientDto.getId())
                .name(ingredientDto.getName().toLowerCase())
                .ingredientUnit(ingredientDto.getIngredientUnit())
                .mealIngredients(ingredientDto.getId() == null ? null : mealIngredientDao.getMealIngredientsByIngredientId(ingredientDto.getId()))
                .category(ingredientDto.getCategory())
                .build();
    }
}
