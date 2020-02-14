package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class MealDaoTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private MealDao mealDao;

    @Test
    public void shouldFindOneMealContainingSpecificStringInName() {
        // given
        Meal meal = getSampleMeal();
        entityManager.persist(meal);

        // when
        List<Meal> searchResult = mealDao.getMealsByPartialName("test", 10);

        // then
        assertEquals(1, searchResult.size());
    }

    @Test
    public void shouldFindOneMealWithTypeDinner() {
        // given
        MealType givenMealType = MealType.DINNER;
        Meal dinnerMeal = getSampleMeal();
        dinnerMeal.setTypeOfMeal(givenMealType);
        entityManager.persist(dinnerMeal);

        Meal otherMeal = getSampleMeal();
        otherMeal.setName("otherMeal");
        entityManager.persist(otherMeal);

        // when
        List<Meal> searchResult = mealDao.getMealsByType(givenMealType);

        // then
        assertEquals(1, searchResult.size());
    }

    @Test
    public void shouldFindOneMealTypeDinner() {
        // given
        MealType givenMealType = MealType.DINNER;
        Meal dinnerMeal = getSampleMeal();
        dinnerMeal.setTypeOfMeal(givenMealType);
        entityManager.persist(dinnerMeal);

        Meal dinnerMealToExclude = getSampleMeal();
        dinnerMealToExclude.setTypeOfMeal(givenMealType);
        dinnerMealToExclude.setName("otherMeal");
        entityManager.persist(dinnerMealToExclude);

        // when
        List<Meal> searchResult = mealDao.getMealsByTypeWithoutCertainMeals(givenMealType, Arrays.asList(dinnerMealToExclude));
        System.out.println(dinnerMealToExclude.getId());

        // then
        assertEquals(1, searchResult.size());
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