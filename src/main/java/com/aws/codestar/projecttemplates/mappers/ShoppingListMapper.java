package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.ShoppingListDto;
import com.aws.codestar.projecttemplates.entities.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper {

    private IngredientMapper ingredientMapper;

    @Autowired
    public ShoppingListMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    public ShoppingListDto toDto(ShoppingList shoppingList) {
        return ShoppingListDto.builder()
                .ingredient(ingredientMapper.toDto(shoppingList.getIngredient()))
                .amount(shoppingList.getAmount())
                .build();
    }

}
