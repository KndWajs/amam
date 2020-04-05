package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.ShoppingElementDTO;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingElement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShoppingElementGenerator {

    public static ShoppingElement getSampleShoppingElementEntity() {
        return ShoppingElement.builder()
                .amount(0.25D)
                .alreadyBought(false)
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static ShoppingElementDTO getSampleShoppingElementDTO() {
        return ShoppingElementDTO.builder()
                .amount(0.25D)
                .alreadyBought(false)
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static List<ShoppingElement> getSampleShoppingElementEntities() {
        List shoppingElements = new ArrayList();
        shoppingElements.add(getSampleShoppingElementEntity());
        return shoppingElements;
    }

    public static List<ShoppingElementDTO> getSampleShoppingElementDTOs() {
        List shoppingElements = new ArrayList();
        shoppingElements.add(getSampleShoppingElementDTO());
        return shoppingElements;
    }
}
