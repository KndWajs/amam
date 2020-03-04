package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingElementDTO {

    private Long id;

    private IngredientDTO ingredient;

    private Double amount;

    private boolean alreadyBought;

}
