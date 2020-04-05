package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;

import java.sql.Timestamp;

public class MealGenerator {

    public static Meal getSampleMealEntity() {
        return new Meal(
                "username",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                5L,
                "test meal",
                MealType.SUPPER,
                PreparingType.BOILED,
                "test recipe",
                66,
                null);

    }

    public static MealDTO getSampleMealDTO() {
        return new MealDTO(5L,
                "username",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                "test meal",
                MealType.SUPPER,
                PreparingType.BOILED,
                "test recipe",
                66,
                null);
    }
}
