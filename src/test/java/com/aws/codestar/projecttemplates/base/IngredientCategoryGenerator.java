package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.IngredientCategoryDTO;
import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.enums.IngredientUnit;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.entities.IngredientCategory;

public class IngredientCategoryGenerator {

    public static IngredientCategory getSampleIngredientCategoryEntity() {
        return IngredientCategory.builder()
                .category("sery")
                .build();
    }

    public static IngredientCategoryDTO getSampleIngredientCategoryDTO() {
        return IngredientCategoryDTO.builder()
                .category("sery")
                .build();
    }
}
