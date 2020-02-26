package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.*;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.mappers.MenuMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.entities.MenuMeal;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingElement;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.persistence.repositories.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class MenuServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void test() {
        // given
        Menu menu = MenuGenerator.getSampleMenuEntity();

        MenuMeal menuMeal = MenuMealGenerator.getSampleMenuMealEntity();
        Meal meal = MealGenerator.getSampleMealEntity();
        entityManager.persist(meal);

        menuMeal.setMeal(meal);

        menu.setMenuMeals(new ArrayList<>());
        menu.getMenuMeals().add(menuMeal);

        entityManager.persist(menu);

        // when
        Menu searchResult = menuRepository.findById(menu.getId()).get();
        MenuMeal secondMenuMeal = MenuMealGenerator.getSampleMenuMealEntity();
        secondMenuMeal.setDayNumber(2);
        secondMenuMeal.setMeal(meal);
        searchResult.getMenuMeals().add(secondMenuMeal);
        searchResult.setName("new name");

        menuService.update(menuMapper.toDTO(searchResult));

        Menu secondSearchresult = menuRepository.findById(menu.getId()).get();

        // then
        assertEquals("new name", secondSearchresult.getName());
        assertEquals(2, secondSearchresult.getMenuMeals().size());
    }
}