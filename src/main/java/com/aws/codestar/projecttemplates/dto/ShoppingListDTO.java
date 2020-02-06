package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ShoppingListDTO {

    private Long id;

    private String name;

    private double numberOfPeople;

    private MenuDTO menu;

    private List<ShoppingElementDTO> shoppingElements;

}
