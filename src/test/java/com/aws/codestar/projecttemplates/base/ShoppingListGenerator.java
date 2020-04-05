package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListGenerator {

    public static ShoppingList getSampleShoppingListEntity() {
        return ShoppingList.builder()
                .name("test shopping list")
                .numberOfPeople(5)
                .archival(false)
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static ShoppingListDTO getSampleShoppingListDTO() {
        return ShoppingListDTO.builder()
                .name("test shopping list")
                .numberOfPeople(5)
                .archival(false)
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static List<ShoppingList> getSampleShoppingListEntities() {
        List shoppingLists = new ArrayList();
        shoppingLists.add(getSampleShoppingListEntity());
        return shoppingLists;
    }

    public static List<ShoppingListDTO> getSampleShoppingListDTOs() {
        List shoppingLists = new ArrayList();
        shoppingLists.add(getSampleShoppingListDTO());
        return shoppingLists;
    }
}
