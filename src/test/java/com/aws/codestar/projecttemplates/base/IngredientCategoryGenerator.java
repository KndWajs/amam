package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.IngredientCategoryDTO;
import com.aws.codestar.projecttemplates.persistence.entities.IngredientCategory;

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
