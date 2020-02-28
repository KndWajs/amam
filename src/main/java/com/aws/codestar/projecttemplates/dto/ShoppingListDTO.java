package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDTO {

    private Long id;

    private String name;

    private double numberOfPeople;

    private List<ShoppingElementDTO> shoppingElements;

    private boolean archival;
}
