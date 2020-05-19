package pl.fit_amam.api.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.base.IngredientGenerator;
import pl.fit_amam.api.base.MealGenerator;
import pl.fit_amam.api.base.MealIngredientGenerator;
import pl.fit_amam.api.dto.MealIngredientDTO;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.MealIngredientMapper;
import pl.fit_amam.api.persistence.entities.Ingredient;
import pl.fit_amam.api.persistence.entities.Meal;
import pl.fit_amam.api.persistence.entities.MealIngredient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class MealIngredientServiceTest extends ServiceTestBase {
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