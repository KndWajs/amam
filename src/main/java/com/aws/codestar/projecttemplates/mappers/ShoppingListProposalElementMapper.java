package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.ShoppingListProposalElementDTO;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingListProposalElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListProposalElementMapper {

    private IngredientMapper ingredientMapper;
    private MenuMapper menuMapper;

    @Autowired
    public ShoppingListProposalElementMapper(IngredientMapper ingredientMapper, MenuMapper menuMapper) {
        this.ingredientMapper = ingredientMapper;
        this.menuMapper = menuMapper;
    }

    public ShoppingListProposalElementDTO toDTO(ShoppingListProposalElement shoppingListProposalElement) {
        return ShoppingListProposalElementDTO.builder()
                .ingredient(ingredientMapper.toDTO(shoppingListProposalElement.getIngredient()))
                .amount(shoppingListProposalElement.getAmount())
                .menu(menuMapper.toDTO(shoppingListProposalElement.getMenu()))
                .build();
    }

}
