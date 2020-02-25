package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.MealIngredientDTO;
import com.aws.codestar.projecttemplates.persistence.entities.MealIngredient;

import java.util.ArrayList;
import java.util.List;

public class MealIngredientGenerator {

    public static MealIngredient getSampleMealIngredientEntity() {
        return MealIngredient.builder()
                .amount(0.25D)
                .build();
    }

    public static MealIngredientDTO getSampleMealIngredientDTO() {
        return MealIngredientDTO.builder()
                .amount(0.25D)
                .build();
    }

    public static List<MealIngredient> getSampleMealIngredientEntities() {
        List mealIngredients = new ArrayList();
        mealIngredients.add(getSampleMealIngredientEntity());
        return mealIngredients;
    }

    public static List<MealIngredientDTO> getSampleMealIngredientDTOs() {
        List mealIngredients = new ArrayList();
        mealIngredients.add(getSampleMealIngredientDTO());
        return mealIngredients;
    }
}
