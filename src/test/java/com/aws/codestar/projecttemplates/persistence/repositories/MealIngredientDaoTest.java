package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.enums.IngredientUnit;
import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class MealIngredientDaoTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private MealIngredientDao mealIngredientDao;

    @Test
    public void shouldFindOneMealIngredientByIngredientId() {
        // given
        MealIngredient mealIngredient = getSampleMealIngredient();
        Ingredient ingredient = getSampleIngredient();
        entityManager.persist(ingredient);
        mealIngredient.setIngredient(ingredient);
        entityManager.persist(mealIngredient);

        MealIngredient anotherMealIngredient = getSampleMealIngredient();
        Ingredient anotherIngredient = getSampleIngredient();
        entityManager.persist(anotherIngredient);
        anotherMealIngredient.setIngredient(anotherIngredient);
        entityManager.persist(anotherMealIngredient);

        // when
        List<MealIngredient> searchResult = mealIngredientDao
                .getMealIngredientsByIngredientId(mealIngredient.getIngredient().getId());

        // then
        assertEquals(1, searchResult.size());
    }

    @Test
    public void shouldFindOneMealIngredientByMealId() {
        // given
        MealIngredient mealIngredient = getSampleMealIngredient();
        Meal meal = getSampleMeal();
        entityManager.persist(meal);
        mealIngredient.setMeal(meal);
        entityManager.persist(mealIngredient);

        MealIngredient anotherMealIngredient = getSampleMealIngredient();
        Meal anotherMeal = getSampleMeal();
        entityManager.persist(anotherMeal);
        anotherMealIngredient.setMeal(anotherMeal);
        entityManager.persist(anotherMealIngredient);

        // when
        List<MealIngredient> searchResult = mealIngredientDao
                .getMealIngredientsByMealId(mealIngredient.getMeal().getId());

        // then
        assertEquals(1, searchResult.size());
    }

    private MealIngredient getSampleMealIngredient() {
        return MealIngredient.builder()
                .amount(0.25D)
                .build();
    }

    private Ingredient getSampleIngredient() {
        return Ingredient.builder()
                .name("carrot")
                .ingredientUnit(IngredientUnit.PCS)
                .category("sery")
                .build();
    }

    private Meal getSampleMeal() {
        return Meal.builder()
                .name("test meal")
                .typeOfMeal(MealType.SUPPER)
                .typeOfPreparing(PreparingType.BOILED)
                .recipe("recipe")
                .minutesToPrepare(66)
                .build();
    }
}