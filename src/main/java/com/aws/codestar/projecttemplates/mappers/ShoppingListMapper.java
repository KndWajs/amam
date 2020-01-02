package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper {

    private IngredientMapper ingredientMapper;

    @Autowired
    public ShoppingListMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    public ShoppingListDTO toDTO(ShoppingList shoppingList) {
        return ShoppingListDTO.builder()
                .ingredient(ingredientMapper.toDTO(shoppingList.getIngredient()))
                .amount(shoppingList.getAmount())
                .build();
    }

}
