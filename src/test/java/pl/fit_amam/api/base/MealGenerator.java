package pl.fit_amam.api.base;

import pl.fit_amam.api.dto.MealDTO;
import pl.fit_amam.api.enums.MealType;
import pl.fit_amam.api.enums.PreparingType;
import pl.fit_amam.api.persistence.entities.Meal;

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
