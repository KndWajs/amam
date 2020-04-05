package pl.fit_amam.api.mappers;

import pl.fit_amam.api.dto.ShoppingListDTO;
import pl.fit_amam.api.persistence.entities.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper {

    private ShoppingElementMapper shoppingElementMapper;

    @Autowired
    public ShoppingListMapper(ShoppingElementMapper shoppingElementMapper) {
        this.shoppingElementMapper = shoppingElementMapper;
    }

    public ShoppingListDTO toDTO(ShoppingList shoppingList) {
        if (shoppingList == null) {
            return null;
        }

        return ShoppingListDTO.builder()
                .id(shoppingList.getId())
                .name(shoppingList.getName())
                .numberOfPeople(shoppingList.getNumberOfPeople())
                .archival(shoppingList.isArchival())
                .shoppingElements(shoppingElementMapper.toDTOs(shoppingList.getShoppingElements()))
                .userName(shoppingList.getUserName())
                .creationDate(shoppingList.getCreationDate())
                .updateDate(shoppingList.getUpdateDate())
                .build();
    }

    public ShoppingList toEntity(ShoppingListDTO shoppingList) {
        if (shoppingList == null) {
            return null;
        }

        return ShoppingList.builder()
                .id(shoppingList.getId())
                .name(shoppingList.getName())
                .numberOfPeople(shoppingList.getNumberOfPeople())
                .archival(shoppingList.isArchival())
                .shoppingElements(
                        shoppingElementMapper.toEntities(shoppingList.getShoppingElements(), shoppingList.getId()))
                .userName(shoppingList.getUserName())
                .creationDate(shoppingList.getCreationDate())
                .updateDate(shoppingList.getUpdateDate())
                .build();
    }

}
