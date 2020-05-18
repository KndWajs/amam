package pl.fit_amam.api.services;

import pl.fit_amam.api.Application;
import pl.fit_amam.api.base.MealGenerator;
import pl.fit_amam.api.base.MenuGenerator;
import pl.fit_amam.api.base.MenuMealGenerator;
import pl.fit_amam.api.controllers.config.H2JpaConfig;
import pl.fit_amam.api.dto.MenuMealDTO;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.MenuMealMapper;
import pl.fit_amam.api.persistence.entities.Meal;
import pl.fit_amam.api.persistence.entities.Menu;
import pl.fit_amam.api.persistence.entities.MenuMeal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class MenuMealServiceTest extends BasicServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private MenuMealService menuMealService;

    @Autowired
    private MenuMealMapper menuMealMapper;

    @Test
    public void shouldReturnMenuMealDTOWhenGetById() {
        // given
        entityManager.persist(createMenuMeal());
        entityManager.persist(createMenuMeal());

        // when
        List<MenuMeal> menuMeals =
                entityManager.createQuery("Select t from MenuMeal t").getResultList();

        // then
        assertEquals(menuMeals.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoMenuMealsInDb() {
        // given
        // when
        List<MenuMeal> menuMeals =
                entityManager.createQuery("Select t from MenuMeal t").getResultList();

        // then
        assertEquals(menuMeals.size(), 0);
    }

    @Test
    public void shouldSaveMenuMeal() {
        // given
        MenuMeal menuMealEntity = createMenuMeal();
        MenuMealDTO menuMealDTO = menuMealMapper.toDTO(menuMealEntity);

        // when
        this.menuMealService.create(menuMealDTO, menuMealEntity.getMenu().getId());
        List<MenuMeal> savedMenuMeals =
                entityManager.createQuery("Select t from MenuMeal t").getResultList();
        MenuMeal savedMenuMeal = savedMenuMeals.get(0);

        // then
        assertEquals(savedMenuMeals.size(), 1);
        assertEquals(menuMealDTO.getDayNumber(), savedMenuMeal.getDayNumber());
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenSavedMenuMealDTOMealIdIsNotInDb() {
        // given
        MenuMeal menuMealEntity = createMenuMeal();
        MenuMealDTO menuMealDTO = menuMealMapper.toDTO(menuMealEntity);
        // when
        // then
        this.menuMealService.create(menuMealDTO, menuMealEntity.getMeal().getId() + 1);
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedMenuMealDTOIsNull() {
        // given
        MenuMealDTO menuMealDTO = null;
        // when
        // then
        this.menuMealService.create(menuMealDTO, 11L);
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenSavedMenuMealDTOMealIdIsNull() {
        // given
        MenuMeal menuMealEntity = createMenuMeal();
        MenuMealDTO menuMealDTO = menuMealMapper.toDTO(menuMealEntity);

        // when
        // then
        this.menuMealService.create(menuMealDTO, null);
    }

    private MenuMeal createMenuMeal() {
        Meal meal = MealGenerator.getSampleMealEntity();
        meal.setId(null);
        entityManager.persist(meal);

        Menu menu = MenuGenerator.getSampleMenuEntity();
        menu.setId(null);
        entityManager.persist(menu);
        entityManager.flush();

        MenuMeal menuMeal = MenuMealGenerator.getSampleMenuMealEntity();
        menuMeal.setMeal(meal);
        menuMeal.setMenu(menu);
        return menuMeal;
    }
}