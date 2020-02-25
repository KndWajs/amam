package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;

public class MealGenerator {

    public static Meal getSampleMealEntity() {
        return Meal.builder()
                .name("test meal")
                .typeOfMeal(MealType.SUPPER)
                .typeOfPreparing(PreparingType.BOILED)
                .recipe("recipe")
                .minutesToPrepare(66)
                .build();
    }

    public static MealDTO getSampleMealDTO() {
        return MealDTO.builder()
                .name("test meal")
                .typeOfMeal(MealType.SUPPER)
                .typeOfPreparing(PreparingType.BOILED)
                .recipe("recipe")
                .minutesToPrepare(66)
                .build();
    }
}
