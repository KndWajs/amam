package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ShoppingElementDTO {

    private Long id;

    private IngredientDTO ingredient;

    private Double amount;

    private boolean alreadyBought;

}
