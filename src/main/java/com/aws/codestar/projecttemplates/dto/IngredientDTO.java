package com.aws.codestar.projecttemplates.dto;

import com.aws.codestar.projecttemplates.enums.IngredientUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private Long id;

    private String name;

    private IngredientUnit ingredientUnit;
}
