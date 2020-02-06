package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.ShoppingElementDTO;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingElement;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingElementMapper {

    private IngredientMapper ingredientMapper;
    private MenuMapper menuMapper;
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingElementMapper(IngredientMapper ingredientMapper, MenuMapper menuMapper, ShoppingListRepository shoppingListRepository) {
        this.ingredientMapper = ingredientMapper;
        this.menuMapper = menuMapper;
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingElementDTO toDTO(ShoppingElement shoppingElement) {
        return ShoppingElementDTO.builder()
                .id(shoppingElement.getId())
//                .shoppingList()
                .ingredient(ingredientMapper.toDTO(shoppingElement.getIngredient()))
                .amount(shoppingElement.getAmount())
                .alreadyBought(shoppingElement.isAlreadyBought())
                .build();
    }

    public ShoppingElement toEntity(ShoppingElementDTO shoppingElementDTO, Long shoppingListId) {
        return ShoppingElement.builder()
                .id(shoppingElementDTO.getId())
                .shoppingList(shoppingListId == null ? null : shoppingListRepository.findById(shoppingListId).get())
                .ingredient(ingredientMapper.toEntity(shoppingElementDTO.getIngredient()))
                .amount(shoppingElementDTO.getAmount())
                .alreadyBought(shoppingElementDTO.isAlreadyBought())
                .build();
    }

    public List<ShoppingElementDTO> toDTOs(List<ShoppingElement> shoppingElements) {
        return shoppingElements.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<ShoppingElement> toEntities(List<ShoppingElementDTO> shoppingElementDTOS, Long shoppingListId) {
        return shoppingElementDTOS.stream().map(dto -> toEntity(dto, shoppingListId)).collect(Collectors.toList());
    }
}
