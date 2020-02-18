package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.enums.IngredientUnit;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;

public class IngredientGenerator {

    public static Ingredient getSampleIngredientEntity() {
        return Ingredient.builder()
                .name("carrot")
                .ingredientUnit(IngredientUnit.PCS)
                .category("sery")
                .build();
    }
}
