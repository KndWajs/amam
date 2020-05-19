package pl.fit_amam.api.persistence.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.base.AbstractIntegrationTestBase;
import pl.fit_amam.api.base.IngredientGenerator;
import pl.fit_amam.api.base.MealGenerator;
import pl.fit_amam.api.base.MealIngredientGenerator;
import pl.fit_amam.api.persistence.entities.Ingredient;
import pl.fit_amam.api.persistence.entities.Meal;
import pl.fit_amam.api.persistence.entities.MealIngredient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class MealIngredientDaoTest extends AbstractIntegrationTestBase {
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