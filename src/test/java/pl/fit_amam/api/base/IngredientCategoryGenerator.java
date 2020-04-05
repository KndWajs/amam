package pl.fit_amam.api.base;

import pl.fit_amam.api.dto.IngredientCategoryDTO;
import pl.fit_amam.api.persistence.entities.IngredientCategory;

import java.sql.Timestamp;

public class IngredientCategoryGenerator {

    public static IngredientCategory getSampleIngredientCategoryEntity() {
        return IngredientCategory.builder()
                .category("sery")
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static IngredientCategoryDTO getSampleIngredientCategoryDTO() {
        return IngredientCategoryDTO.builder()
                .category("sery")
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
