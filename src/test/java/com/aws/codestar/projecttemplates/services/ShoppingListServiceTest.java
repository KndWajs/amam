package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.IngredientGenerator;
import com.aws.codestar.projecttemplates.base.ShoppingElementGenerator;
import com.aws.codestar.projecttemplates.base.ShoppingListGenerator;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.exceptions.EmptyRequiredFieldException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.ShoppingListMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingElement;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class ShoppingListServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private ShoppingListMapper shoppingListMapper;

    @Test
    public void shouldReturnTwoShoppingListsDTOWhenGetAllNotArchivalShoppingLists() {
        // given
        entityManager.persist(createAndSaveShoppingList());
        entityManager.persist(createAndSaveShoppingList());

        // when
        List<ShoppingListDTO> shoppingLists = this.shoppingListService.getShoppingLists(false);

        // then
        assertEquals(shoppingLists.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoShoppingListsInDb() {
        // given
        // when
        List<ShoppingListDTO> shoppingLists = this.shoppingListService.getShoppingLists(false);

        // then
        assertEquals(shoppingLists.size(), 0);
    }

    @Test
    public void shouldSaveShoppingList() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createAndSaveShoppingList());

        // when
        ShoppingListDTO shoppingList = this.shoppingListService.create(shoppingListDTO);
        List<ShoppingListDTO> savedShoppingLists = this.shoppingListService.getShoppingLists(false);
        ShoppingListDTO savedShoppingList = this.shoppingListService.get(shoppingList.getId());

        // then
        assertEquals(savedShoppingLists.size(), 1);
        assertEquals(shoppingListDTO.getName(), savedShoppingList.getName());
    }

    @Test
    public void shouldSaveShoppingListWhenIdIsNotNull() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createAndSaveShoppingList());
        shoppingListDTO.setId(323L);

        // when
        ShoppingListDTO shoppingList = this.shoppingListService.create(shoppingListDTO);
        List<ShoppingListDTO> savedShoppingLists = this.shoppingListService.getShoppingLists(false);
        ShoppingListDTO savedShoppingList = this.shoppingListService.get(shoppingList.getId());

        // then
        assertEquals(savedShoppingLists.size(), 1);
        assertEquals(shoppingListDTO.getName(), savedShoppingList.getName());
    }

    @Test
    public void shouldReturnShoppingListDTOWhenUpdate() {
        // given
        ShoppingListDTO shoppingListDTO =
                this.shoppingListService.create(shoppingListMapper.toDTO(createAndSaveShoppingList()));

        // when
        ShoppingListDTO updatedShoppingList = this.shoppingListService.update(shoppingListDTO);

        // then
        assertEquals(shoppingListDTO, updatedShoppingList);
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenUpdateShoppingListDTOIdIsNotInDb() {
        // given
        ShoppingListDTO shoppingListDTO =
                this.shoppingListService.create(shoppingListMapper.toDTO(createAndSaveShoppingList()));

        // when
        // then
        shoppingListDTO.setId(shoppingListDTO.getId() + 1);
        this.shoppingListService.update(shoppingListDTO);
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedShoppingListDTOIsNull() {
        // given
        ShoppingListDTO shoppingListDTO = null;
        // when
        // then
        this.shoppingListService.create(shoppingListDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedShoppingListDTOHasEmptyNameString() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createAndSaveShoppingList());
        shoppingListDTO.setName("");

        // when
        // then
        this.shoppingListService.create(shoppingListDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedShoppingListDTOHasNullInNameString() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createAndSaveShoppingList());
        shoppingListDTO.setName(null);

        // when
        // then
        this.shoppingListService.create(shoppingListDTO);
    }

    private ShoppingList createAndSaveShoppingList() {
        ShoppingList shoppingList = ShoppingListGenerator.getSampleShoppingListEntity();
        ShoppingElement shoppingElement = ShoppingElementGenerator.getSampleShoppingElementEntity();
        Ingredient ingredient = IngredientGenerator.getSampleIngredientEntity();
        entityManager.persist(ingredient);
        shoppingElement.setIngredient(ingredient);
        shoppingList.setShoppingElements(new ArrayList<>());
        shoppingList.getShoppingElements().add(shoppingElement);
        return shoppingList;
    }
}