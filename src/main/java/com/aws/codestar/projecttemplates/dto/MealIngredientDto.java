package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MealIngredientDto {
    private Long id;
    private Long mealId;
    private IngredientDto ingredient;
    private Double amount;
}
