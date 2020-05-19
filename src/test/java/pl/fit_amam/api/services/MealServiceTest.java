package pl.fit_amam.api.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.base.MealGenerator;
import pl.fit_amam.api.dto.MealDTO;
import pl.fit_amam.api.exceptions.EmptyRequiredFieldException;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.MealMapper;
import pl.fit_amam.api.persistence.entities.Meal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class MealServiceTest extends ServiceTestBase {
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