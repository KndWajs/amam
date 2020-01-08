package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.ShoppingElementDTO;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingElementMapper {

    private IngredientMapper ingredientMapper;
    private MenuMapper menuMapper;

    @Autowired
    public ShoppingElementMapper(IngredientMapper ingredientMapper, MenuMapper menuMapper) {
        this.ingredientMapper = ingredientMapper;
        this.menuMapper = menuMapper;
    }

    public ShoppingElementDTO toDTO(ShoppingElement shoppingElement) {
        return ShoppingElementDTO.builder()
                .ingredient(ingredientMapper.toDTO(shoppingElement.getIngredient()))
                .amount(shoppingElement.getAmount())
                .menu(menuMapper.toDTO(shoppingElement.getMenu()))
                .build();
    }

}
