package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.IngredientGenerator;
import com.aws.codestar.projecttemplates.base.MealGenerator;
import com.aws.codestar.projecttemplates.base.MealIngredientGenerator;
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
        MealIngredient mealIngredient = MealIngredientGenerator.getSampleMealIngredientEntity();
        Ingredient ingredient = IngredientGenerator.getSampleIngredientEntity();
        entityManager.persist(ingredient);
        mealIngredient.setIngredient(ingredient);
        entityManager.persist(mealIngredient);

        MealIngredient anotherMealIngredient = MealIngredientGenerator.getSampleMealIngredientEntity();
        Ingredient anotherIngredient = IngredientGenerator.getSampleIngredientEntity();
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
        MealIngredient mealIngredient = MealIngredientGenerator.getSampleMealIngredientEntity();
        Meal meal = MealGenerator.getSampleMealEntity();
        entityManager.persist(meal);
        mealIngredient.setMeal(meal);
        entityManager.persist(mealIngredient);

        MealIngredient anotherMealIngredient = MealIngredientGenerator.getSampleMealIngredientEntity();
        Meal anotherMeal = MealGenerator.getSampleMealEntity();
        entityManager.persist(anotherMeal);
        anotherMealIngredient.setMeal(anotherMeal);
        entityManager.persist(anotherMealIngredient);

        // when
        List<MealIngredient> searchResult = mealIngredientDao
                .getMealIngredientsByMealId(mealIngredient.getMeal().getId());

        // then
        assertEquals(1, searchResult.size());
    }
}