package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.MealGenerator;
import com.aws.codestar.projecttemplates.base.MenuGenerator;
import com.aws.codestar.projecttemplates.base.MenuMealGenerator;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.mappers.MenuMapper;
import com.aws.codestar.projecttemplates.mappers.MenuMealMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.persistence.entities.MenuMeal;
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

    @Autowired
    private MenuMealMapper menuMealMapper;

    @Test
    @Transactional
    public void test() {
        // given
        Menu menu = MenuGenerator.getSampleMenuEntity();

        MenuMeal menuMeal = MenuMealGenerator.getSampleMenuMealEntity();
        Meal meal = MealGenerator.getSampleMealEntity();
        meal.setId(null);
        entityManager.persist(meal);
        menuMeal.setMeal(meal);

        MenuMeal menuMeal2 = MenuMealGenerator.getSampleMenuMealEntity();
        menuMeal2.setDayNumber(2);
        menuMeal2.setMeal(meal);

//        menu.setMenuMeals(new ArrayList<>());
//        menu.getMenuMeals().add(menuMeal);
//        menu.getMenuMeals().add(menuMeal2);

        entityManager.persist(menu);
        entityManager.flush();

        // when
        MenuDTO menuDTOTemp = menuService.get(menu.getId());

        MenuDTO menuDTO = MenuDTO.builder()
                .id(menuDTOTemp.getId())
                .numberOfPeople(menuDTOTemp.getNumberOfPeople())
                .name(menuDTOTemp.getName())
                .archival(menuDTOTemp.isArchival())
                .meals(new ArrayList<>())
                .build();

        MenuMeal secondMenuMeal = MenuMealGenerator.getSampleMenuMealEntity();
        secondMenuMeal.setDayNumber(3);
        secondMenuMeal.setMeal(meal);

        menuDTO.setName("new name");
        menuDTO.getMeals().add(menuMealMapper.toDTO(secondMenuMeal));

        menuService.update(menuDTO);
        entityManager.flush();

        Menu secondSearchresult = menuRepository.findById(menuDTO.getId()).get();
        entityManager.flush();

        // then
        assertEquals("new name", secondSearchresult.getName());
//        assertEquals(1, menu.getMenuMeals().size());
//        assertEquals(3, secondSearchresult.getMenuMeals().size());
    }
}