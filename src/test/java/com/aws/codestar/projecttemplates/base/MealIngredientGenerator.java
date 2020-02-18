package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.persistence.entities.MealIngredient;

public class MealIngredientGenerator {

    public static MealIngredient getSampleMealIngredientEntity() {
        return MealIngredient.builder()
                .amount(0.25D)
                .build();
    }
}
