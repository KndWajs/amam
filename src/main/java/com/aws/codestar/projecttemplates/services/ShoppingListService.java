package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.*;
import com.aws.codestar.projecttemplates.exceptions.EmptyRequiredFieldException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.ShoppingListMapper;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingListDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShoppingListService {
    private ShoppingListDao shoppingListDao;
    private ShoppingListMapper shoppingListMapper;
    private ShoppingElementService shoppingElementService;

    @Autowired
    public ShoppingListService(ShoppingListMapper shoppingListMapper, ShoppingElementService shoppingElementService,
                               ShoppingListDao shoppingListDao) {
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingElementService = shoppingElementService;
        this.shoppingListDao = shoppingListDao;
    }

    public ShoppingListDTO create(ShoppingListDTO shoppingListDTO) throws ObjectIsNullException {
        validateShoppingListObject(shoppingListDTO);

        shoppingListDTO.setShoppingElements(new ArrayList<>());
        ShoppingListDTO savedShoppingList = shoppingListMapper
                .toDTO(shoppingListDao.getRepository().save(shoppingListMapper.toEntity(shoppingListDTO)));

        if (!(shoppingListDTO.getShoppingElements() == null || shoppingListDTO.getShoppingElements().isEmpty())) {
            List<ShoppingElementDTO> shoppingElements = shoppingListDTO.getShoppingElements();
            for (ShoppingElementDTO shoppingElementDTO : shoppingElements) {
                savedShoppingList.getShoppingElements().add(shoppingElementDTO);
            }
        }

        return update(savedShoppingList);
    }

    public ShoppingListDTO create(MenuDTO menuDTO) throws ObjectIsNullException {
        //TODO refactor this method
        ShoppingListDTO shoppingList = ShoppingListDTO.builder()
                .name(menuDTO.getName())
                .archival(false)
                .numberOfPeople(menuDTO.getNumberOfPeople())
                .shoppingElements(new ArrayList<>())
                .build();

        for (MenuMealDTO menuMeal : menuDTO.getMeals()) {
            for (MealIngredientDTO mealIngredient : menuMeal.getMeal().getIngredients()) {
                int shoppingElementIndex = shoppingList.getShoppingElements().stream()
                        .map(s -> s.getIngredient())
                        .collect(Collectors.toList()).indexOf(mealIngredient.getIngredient());

                if (shoppingElementIndex < 0) {
                    shoppingList.getShoppingElements().add(
                            ShoppingElementDTO.builder()
                                    .amount(mealIngredient.getAmount() * menuDTO.getNumberOfPeople())
                                    .alreadyBought(false)
                                    .ingredient(mealIngredient.getIngredient())
                                    .build());
                } else {
                    shoppingList.getShoppingElements().get(shoppingElementIndex).setAmount(
                            shoppingList.getShoppingElements().get(shoppingElementIndex).getAmount()
                                    + mealIngredient.getAmount() * menuDTO.getNumberOfPeople());
                }
            }
        }

        return create(shoppingList);
    }

    @Transactional(readOnly = true)
    public List<ShoppingListDTO> getAll(boolean archival) {
        List<ShoppingListDTO> shoppingListDTOS = new ArrayList<>();
        for (ShoppingList shoppingList : shoppingListDao.getShoppingListsByArchivalStatus(archival)) {
            shoppingListDTOS.add(shoppingListMapper.toDTO(shoppingList));
        }
        return shoppingListDTOS;
    }

    @Transactional(readOnly = true)
    public ShoppingListDTO get(Long id) {
        validateShoppingListId(id);
        return shoppingListMapper.toDTO(shoppingListDao.getRepository().findById(id).get());
    }

    public ShoppingListDTO update(ShoppingListDTO shoppingListDTO) {
        validateShoppingListObject(shoppingListDTO);
        validateShoppingListId(shoppingListDTO.getId());

        ShoppingListDTO savedShoppingList = shoppingListMapper
                .toDTO(shoppingListDao.getRepository().save(shoppingListMapper.toEntity(shoppingListDTO)));

        return savedShoppingList;
    }

    public void delete(Long id) {
        validateShoppingListId(id);
        shoppingListDao.getRepository().deleteById(id);
    }

    private void validateShoppingListId(Long shoppingListId) {
        if (shoppingListId == null || !shoppingListDao.getRepository().existsById(shoppingListId)) {
            throw new ObjectIdDoesNotExistsException(shoppingListId);
        }
    }

    private void validateShoppingListObject(ShoppingListDTO shoppingList) throws ObjectIsNullException {
        if (shoppingList == null) {
            throw new ObjectIsNullException(ShoppingListDTO.class.getName());
        }
        if (StringUtils.isEmpty(shoppingList.getName())) {
            throw new EmptyRequiredFieldException("Name");
        }
    }

}
