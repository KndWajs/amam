package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;

public class MealGenerator {

    public static Meal getSampleMealEntity() {
        return new Meal(5L,
                "test meal",
                MealType.SUPPER,
                PreparingType.BOILED,
                "test recipe",
                66,
                null);

    }

    public static MealDTO getSampleMealDTO() {
        return new MealDTO(5L,
                "test meal",
                MealType.SUPPER,
                PreparingType.BOILED,
                "test recipe",
                66,
                null);
    }
}
