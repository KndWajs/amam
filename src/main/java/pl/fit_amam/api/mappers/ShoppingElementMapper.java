package pl.fit_amam.api.mappers;

import pl.fit_amam.api.dto.ShoppingElementDTO;
import pl.fit_amam.api.persistence.entities.ShoppingElement;
import pl.fit_amam.api.persistence.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingElementMapper {

    private IngredientMapper ingredientMapper;
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingElementMapper(IngredientMapper ingredientMapper, ShoppingListRepository shoppingListRepository) {
        this.ingredientMapper = ingredientMapper;
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingElementDTO toDTO(ShoppingElement shoppingElement) {
        if (shoppingElement == null) {
            return null;
        }

        return ShoppingElementDTO.builder()
                .id(shoppingElement.getId())
                .ingredient(ingredientMapper.toDTO(shoppingElement.getIngredient()))
                .amount(shoppingElement.getAmount())
                .alreadyBought(shoppingElement.isAlreadyBought())
                .userName(shoppingElement.getUserName())
                .creationDate(shoppingElement.getCreationDate())
                .updateDate(shoppingElement.getUpdateDate())
                .build();
    }

    public ShoppingElement toEntity(ShoppingElementDTO shoppingElement, Long shoppingListId) {
        if (shoppingElement == null) {
            return null;
        }

        return ShoppingElement.builder()
                .id(shoppingElement.getId())
                .shoppingList(shoppingListId == null ? null : shoppingListRepository.findById(shoppingListId).get())
                .ingredient(ingredientMapper.toEntity(shoppingElement.getIngredient()))
                .amount(shoppingElement.getAmount())
                .alreadyBought(shoppingElement.isAlreadyBought())
                .userName(shoppingElement.getUserName())
                .creationDate(shoppingElement.getCreationDate())
                .updateDate(shoppingElement.getUpdateDate())
                .build();
    }

    public List<ShoppingElementDTO> toDTOs(List<ShoppingElement> shoppingElements) {
        if (shoppingElements == null) {
            return null;
        }

        return shoppingElements.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<ShoppingElement> toEntities(List<ShoppingElementDTO> shoppingElements, Long shoppingListId) {
        if (shoppingElements == null) {
            return null;
        }

        return shoppingElements.stream().map(dto -> toEntity(dto, shoppingListId)).collect(Collectors.toList());
    }
}
