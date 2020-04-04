package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.IngredientGenerator;
import com.aws.codestar.projecttemplates.base.MealGenerator;
import com.aws.codestar.projecttemplates.base.MealIngredientGenerator;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.dto.MealIngredientDTO;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.MealIngredientMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.entities.MealIngredient;
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
public class MealIngredientServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private MealIngredientService mealIngredientService;

    @Autowired
    private MealIngredientMapper mealIngredientMapper;

    @Test
    public void shouldReturnMealIngredientDTOWhenGetById() {
        // given
        entityManager.persist(createMealIngredient());
        entityManager.persist(createMealIngredient());

        // when
        List<MealIngredient> mealIngredients =
                entityManager.createQuery("Select t from MealIngredient t").getResultList();

        // then
        assertEquals(mealIngredients.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoMealIngredientsInDb() {
        // given
        // when
        List<MealIngredient> mealIngredients =
                entityManager.createQuery("Select t from MealIngredient t").getResultList();

        // then
        assertEquals(mealIngredients.size(), 0);
    }

    @Test
    public void shouldSaveMealIngredient() {
        // given
        MealIngredient mealIngredientEntity = createMealIngredient();
        MealIngredientDTO mealIngredientDTO = mealIngredientMapper.toDTO(mealIngredientEntity);

        // when
        this.mealIngredientService.create(mealIngredientDTO, mealIngredientEntity.getMeal().getId());
        List<MealIngredient> savedMealIngredients =
                entityManager.createQuery("Select t from MealIngredient t").getResultList();
        MealIngredient savedMealIngredient = savedMealIngredients.get(0);

        // then
        assertEquals(savedMealIngredients.size(), 1);
        assertEquals(mealIngredientDTO.getAmount(), savedMealIngredient.getAmount());
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenSavedMealIngredientDTOMealIdIsNotInDb() {
        // given
        MealIngredient mealIngredientEntity = createMealIngredient();
        MealIngredientDTO mealIngredientDTO = mealIngredientMapper.toDTO(mealIngredientEntity);
        // when
        // then
        this.mealIngredientService.create(mealIngredientDTO, mealIngredientEntity.getMeal().getId() + 1);
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedMealIngredientDTOIsNull() {
        // given
        MealIngredientDTO mealIngredientDTO = null;
        // when
        // then
        this.mealIngredientService.create(mealIngredientDTO, 11L);
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenSavedMealIngredientDTOMealIdIsNull() {
        // given
        MealIngredient mealIngredientEntity = createMealIngredient();
        MealIngredientDTO mealIngredientDTO = mealIngredientMapper.toDTO(mealIngredientEntity);

        // when
        // then
        this.mealIngredientService.create(mealIngredientDTO, null);
    }

    private MealIngredient createMealIngredient() {
        Meal meal = MealGenerator.getSampleMealEntity();
        meal.setId(null);
        entityManager.persist(meal);

        Ingredient ingredient = IngredientGenerator.getSampleIngredientEntity();
        ingredient.setId(null);
        entityManager.persist(ingredient);
        entityManager.flush();

        MealIngredient mealIngredient = MealIngredientGenerator.getSampleMealIngredientEntity();
        mealIngredient.setMeal(meal);
        mealIngredient.setIngredient(ingredient);
        return mealIngredient;
    }
}