package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper {

    private IngredientMapper ingredientMapper;
    private MenuMapper menuMapper;
    private ShoppingElementMapper shoppingElementMapper;

    @Autowired
    public ShoppingListMapper(IngredientMapper ingredientMapper, MenuMapper menuMapper, ShoppingElementMapper shoppingElementMapper) {
        this.ingredientMapper = ingredientMapper;
        this.menuMapper = menuMapper;
        this.shoppingElementMapper = shoppingElementMapper;
    }

    public ShoppingListDTO toDTO(ShoppingList shoppingElement) {
        return ShoppingListDTO.builder()
                .id(shoppingElement.getId())
                .name(shoppingElement.getName())
                .numberOfPeople(shoppingElement.getNumberOfPeople())
                .menu(shoppingElement.getMenu() == null ? null : menuMapper.toDTO(shoppingElement.getMenu()))
                .shoppingElements(shoppingElementMapper.toDTOs(shoppingElement.getShoppingElements()))
                .build();
    }

    public ShoppingList toEntity(ShoppingListDTO shoppingElementDTO) {
        return ShoppingList.builder()
                .id(shoppingElementDTO.getId())
                .name(shoppingElementDTO.getName())
                .numberOfPeople(shoppingElementDTO.getNumberOfPeople())
                .menu(shoppingElementDTO.getMenu() == null ? null :menuMapper.toEntity(shoppingElementDTO.getMenu()))
                .shoppingElements(shoppingElementMapper.toEntities(shoppingElementDTO.getShoppingElements(), shoppingElementDTO.getId()))
                .build();
    }

}
