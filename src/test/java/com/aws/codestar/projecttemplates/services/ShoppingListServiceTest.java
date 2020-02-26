package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.IngredientGenerator;
import com.aws.codestar.projecttemplates.base.MealGenerator;
import com.aws.codestar.projecttemplates.base.ShoppingElementGenerator;
import com.aws.codestar.projecttemplates.base.ShoppingListGenerator;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.mappers.ShoppingListMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingElement;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import com.aws.codestar.projecttemplates.persistence.repositories.MealDao;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingListRepository;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class ShoppingListServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private ShoppingListMapper shoppingListMapper;

    @Test
    public void test() {
        // given
        ShoppingList shoppingList = ShoppingListGenerator.getSampleShoppingListEntity();
        ShoppingElement shoppingElement = ShoppingElementGenerator.getSampleShoppingElementEntity();
        shoppingElement.setIngredient(IngredientGenerator.getSampleIngredientEntity());
        shoppingList.setShoppingElements(new ArrayList<>());
        shoppingList.getShoppingElements().add(shoppingElement);
        entityManager.persist(shoppingList);

        // when
        ShoppingList searchResult = shoppingListRepository.findById(shoppingList.getId()).get();
        searchResult.getShoppingElements().get(0).setAmount(55D);

        shoppingListService.update(shoppingListMapper.toDTO(searchResult));

        ShoppingList secondSearchresult = shoppingListRepository.findById(shoppingList.getId()).get();


        // then
        assertEquals(shoppingList.getName(), searchResult.getName());
        assertEquals(Double.valueOf(55), secondSearchresult.getShoppingElements().get(0).getAmount());
    }
}