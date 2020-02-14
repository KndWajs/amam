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
    public void shouldFindMeal() {
        // given
        Meal meal = Meal.builder()
                .name("test meal")
                .typeOfMeal(MealType.DINNER)
                .typeOfPreparing(PreparingType.BOILED)
                .recipe("recipe")
                .minutesToPrepare(66)
                .build();
        entityManager.persist(meal);

        // when
        List<Meal> searchResult = mealDao.getMealsByPartialName("test", 1);

        // then
        assertEquals(1, searchResult.size());
    }
}