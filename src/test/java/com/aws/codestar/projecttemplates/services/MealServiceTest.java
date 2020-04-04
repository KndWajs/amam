package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.MealGenerator;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.exceptions.EmptyRequiredFieldException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.MealMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
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
public class MealServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private MealService mealService;

    @Autowired
    private MealMapper mealMapper;

    @Test
    public void shouldReturnTwoMealsDTOWhenGetAllMeals() {
        // given
        entityManager.persist(createMeal());
        entityManager.persist(createMeal());

        // when
        List<MealDTO> meals = this.mealService.getAll();

        // then
        assertEquals(meals.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoMealsInDb() {
        // given
        // when
        List<MealDTO> meals = this.mealService.getAll();

        // then
        assertEquals(meals.size(), 0);
    }

    @Test
    public void shouldSaveMeal() {
        // given
        MealDTO mealDTO = mealMapper.toDTO(createMeal());

        // when
        MealDTO meal = this.mealService.create(mealDTO);
        List<MealDTO> savedMeals = this.mealService.getAll();
        MealDTO savedMeal = this.mealService.get(meal.getId());

        // then
        assertEquals(savedMeals.size(), 1);
        assertEquals(mealDTO.getName(), savedMeal.getName());
    }

    @Test
    public void shouldSaveMealWithoutMealIngredients() {
        // given
        MealDTO mealDTO = mealMapper.toDTO(createMeal());
        mealDTO.setIngredients(null);

        // when
        MealDTO meal = this.mealService.create(mealDTO);
        List<MealDTO> savedMeals = this.mealService.getAll();
        MealDTO savedMeal = this.mealService.get(meal.getId());

        // then
        assertEquals(savedMeals.size(), 1);
        assertEquals(mealDTO.getName(), savedMeal.getName());
    }

    @Test
    public void shouldSaveMealWhenIdIsNotNull() {
        // given
        MealDTO mealDTO = mealMapper.toDTO(createMeal());
        mealDTO.setId(323L);

        // when
        MealDTO meal = this.mealService.create(mealDTO);
        List<MealDTO> savedMeals = this.mealService.getAll();
        MealDTO savedMeal = this.mealService.get(meal.getId());

        // then
        assertEquals(savedMeals.size(), 1);
        assertEquals(mealDTO.getName(), savedMeal.getName());
    }

    @Test
    public void shouldReturnMealDTOWhenUpdate() {
        // given
        MealDTO mealDTO =
                this.mealService.create(mealMapper.toDTO(createMeal()));

        // when
        MealDTO updatedMeal = this.mealService.update(mealDTO);

        // then
        assertEquals(mealDTO, updatedMeal);
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenUpdateMealDTOIdIsNotInDb() {
        // given
        MealDTO mealDTO =
                this.mealService.create(mealMapper.toDTO(createMeal()));

        // when
        // then
        mealDTO.setId(mealDTO.getId() + 1);
        this.mealService.update(mealDTO);
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedMealDTOIsNull() {
        // given
        MealDTO mealDTO = null;
        // when
        // then
        this.mealService.create(mealDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedMealDTOHasEmptyNameString() {
        // given
        MealDTO mealDTO = mealMapper.toDTO(createMeal());
        mealDTO.setName("");

        // when
        // then
        this.mealService.create(mealDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedMealDTOHasNullInNameString() {
        // given
        MealDTO mealDTO = mealMapper.toDTO(createMeal());
        mealDTO.setName(null);

        // when
        // then
        this.mealService.create(mealDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedMealDTOHasNullInTypeOfMeal() {
        // given
        MealDTO mealDTO = mealMapper.toDTO(createMeal());
        mealDTO.setTypeOfMeal(null);

        // when
        // then
        this.mealService.create(mealDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedMealDTOHasNullInTypeOfPreparing() {
        // given
        MealDTO mealDTO = mealMapper.toDTO(createMeal());
        mealDTO.setTypeOfPreparing(null);

        // when
        // then
        this.mealService.create(mealDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedMealDTOHasEmptyMinutesToPrepareField() {
        // given
        MealDTO mealDTO = mealMapper.toDTO(createMeal());
        mealDTO.setMinutesToPrepare(null);

        // when
        // then
        this.mealService.create(mealDTO);
    }

    private Meal createMeal() {
        Meal meal = MealGenerator.getSampleMealEntity();
        meal.setId(null);

        return meal;
    }
}