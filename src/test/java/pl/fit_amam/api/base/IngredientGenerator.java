package pl.fit_amam.api.base;

import pl.fit_amam.api.dto.IngredientDTO;
import pl.fit_amam.api.enums.IngredientUnit;
import pl.fit_amam.api.persistence.entities.Ingredient;

import java.sql.Timestamp;

public class IngredientGenerator {

    public static Ingredient getSampleIngredientEntity() {
        return Ingredient.builder()
                .name("carrot")
                .ingredientUnit(IngredientUnit.PCS)
                .category("sery")
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static IngredientDTO getSampleIngredientDTO() {
        return IngredientDTO.builder()
                .name("carrot")
                .ingredientUnit(IngredientUnit.PCS)
                .category("sery")
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
