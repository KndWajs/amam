package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.MealGenerator;
import com.aws.codestar.projecttemplates.base.MenuGenerator;
import com.aws.codestar.projecttemplates.base.MenuMealGenerator;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.exceptions.EmptyRequiredFieldException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.MenuMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.persistence.entities.MenuMeal;
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
public class MenuServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void shouldReturnTwoMenusDTOWhenGetAllNotArchivalMenus() {
        // given
        entityManager.persist(createMenu());
        entityManager.persist(createMenu());

        // when
        List<MenuDTO> menus = this.menuService.getAll(false);

        // then
        assertEquals(menus.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoMenusInDb() {
        // given
        // when
        List<MenuDTO> menus = this.menuService.getAll(false);

        // then
        assertEquals(menus.size(), 0);
    }

    @Test
    public void shouldSaveMenu() {
        // given
        MenuDTO menuDTO = menuMapper.toDTO(createMenu());

        // when
        MenuDTO menu = this.menuService.create(menuDTO);
        List<MenuDTO> savedMenus = this.menuService.getAll(false);
        MenuDTO savedMenu = this.menuService.get(menu.getId());

        // then
        assertEquals(savedMenus.size(), 1);
        assertEquals(menuDTO.getName(), savedMenu.getName());
    }

    @Test
    public void shouldSaveMenuWithoutMenuMeals() {
        // given
        MenuDTO menuDTO = menuMapper.toDTO(createMenu());
        menuDTO.setMeals(null);

        // when
        MenuDTO menu = this.menuService.create(menuDTO);
        List<MenuDTO> savedMenus = this.menuService.getAll(false);
        MenuDTO savedMenu = this.menuService.get(menu.getId());

        // then
        assertEquals(savedMenus.size(), 1);
        assertEquals(menuDTO.getName(), savedMenu.getName());
    }

    @Test
    public void shouldSaveMenuWhenIdIsNotNull() {
        // given
        MenuDTO menuDTO = menuMapper.toDTO(createMenu());
        menuDTO.setId(323L);

        // when
        MenuDTO menu = this.menuService.create(menuDTO);
        List<MenuDTO> savedMenus = this.menuService.getAll(false);
        MenuDTO savedMenu = this.menuService.get(menu.getId());

        // then
        assertEquals(savedMenus.size(), 1);
        assertEquals(menuDTO.getName(), savedMenu.getName());
    }

    @Test
    public void shouldReturnMenuDTOWhenUpdate() {
        // given
        MenuDTO menuDTO = this.menuService.create(menuMapper.toDTO(createMenu()));

        // when
        MenuDTO updatedMenu = this.menuService.update(menuDTO);

        // then
        assertEquals(menuDTO, updatedMenu);
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenUpdateMenuDTOIdIsNotInDb() {
        // given
        MenuDTO menuDTO =
                this.menuService.create(menuMapper.toDTO(createMenu()));

        // when
        // then
        menuDTO.setId(menuDTO.getId() + 1);
        this.menuService.update(menuDTO);
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedMenuDTOIsNull() {
        // given
        MenuDTO menuDTO = null;
        // when
        // then
        this.menuService.create(menuDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedMenuDTOHasEmptyNameString() {
        // given
        MenuDTO menuDTO = menuMapper.toDTO(createMenu());
        menuDTO.setName("");

        // when
        // then
        this.menuService.create(menuDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedMenuDTOHasNullInNameString() {
        // given
        MenuDTO menuDTO = menuMapper.toDTO(createMenu());
        menuDTO.setName(null);

        // when
        // then
        this.menuService.create(menuDTO);
    }

    private Menu createMenu() {
        Menu menu = MenuGenerator.getSampleMenuEntity();

        MenuMeal menuMeal = MenuMealGenerator.getSampleMenuMealEntity();
        Meal meal = MealGenerator.getSampleMealEntity();
        meal.setId(null);
        entityManager.persist(meal);
        menuMeal.setMeal(meal);
        menu.setMenuMeals(new ArrayList<>());
        menu.getMenuMeals().add(menuMeal);

        return menu;
    }
}