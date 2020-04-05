package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.Application;
import pl.fit_amam.api.base.IngredientGenerator;
import pl.fit_amam.api.base.MealGenerator;
import pl.fit_amam.api.base.MealIngredientGenerator;
import pl.fit_amam.api.controllers.config.H2JpaConfig;
import pl.fit_amam.api.persistence.entities.Ingredient;
import pl.fit_amam.api.persistence.entities.Meal;
import pl.fit_amam.api.persistence.entities.MealIngredient;
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
public class MealIngredientDaoTest {
    @PersistenceContext
    private EntityManager entityManager;

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
        meal.setId(null);
        entityManager.persist(meal);
        mealIngredient.setMeal(meal);
        entityManager.persist(mealIngredient);

        MealIngredient anotherMealIngredient = MealIngredientGenerator.getSampleMealIngredientEntity();
        Meal anotherMeal = MealGenerator.getSampleMealEntity();
        anotherMeal.setId(null);
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