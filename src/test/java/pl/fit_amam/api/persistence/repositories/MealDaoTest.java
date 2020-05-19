package pl.fit_amam.api.persistence.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.base.AbstractIntegrationTestBase;
import pl.fit_amam.api.base.MealGenerator;
import pl.fit_amam.api.enums.MealType;
import pl.fit_amam.api.persistence.entities.Meal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class MealDaoTest extends AbstractIntegrationTestBase {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MealDao mealDao;

    @Test
    public void shouldFindOneMealContainingSpecificStringInName() {
        // given
        Meal meal = MealGenerator.getSampleMealEntity();
        meal.setId(null);
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
        Meal dinnerMeal = MealGenerator.getSampleMealEntity();
        dinnerMeal.setId(null);
        dinnerMeal.setTypeOfMeal(givenMealType);
        entityManager.persist(dinnerMeal);

        Meal otherMeal = MealGenerator.getSampleMealEntity();
        otherMeal.setName("otherMeal");
        otherMeal.setId(null);
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
        Meal dinnerMeal = MealGenerator.getSampleMealEntity();
        dinnerMeal.setId(null);
        dinnerMeal.setTypeOfMeal(givenMealType);
        entityManager.persist(dinnerMeal);

        Meal dinnerMealToExclude = MealGenerator.getSampleMealEntity();
        dinnerMealToExclude.setId(null);
        dinnerMealToExclude.setTypeOfMeal(givenMealType);
        dinnerMealToExclude.setName("otherMeal");
        entityManager.persist(dinnerMealToExclude);

        // when
        List<Meal> searchResult = mealDao.getMealsByTypeWithoutCertainMeals(givenMealType, Arrays.asList(dinnerMealToExclude));
        System.out.println(dinnerMealToExclude.getId());

        // then
        assertEquals(1, searchResult.size());
    }
}