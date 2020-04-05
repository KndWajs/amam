package pl.fit_amam.api.mappers;

import pl.fit_amam.api.base.MealGenerator;
import pl.fit_amam.api.base.MealIngredientGenerator;
import pl.fit_amam.api.dto.MealIngredientDTO;
import pl.fit_amam.api.persistence.entities.Meal;
import pl.fit_amam.api.persistence.entities.MealIngredient;
import pl.fit_amam.api.persistence.repositories.MealRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealIngredientMapperTest {
    @InjectMocks
    private MealIngredientMapper mealIngredientMapper;

    @Mock
    private IngredientMapper ingredientMapper;

    @Mock
    private MealRepository mealRepository;

    @Before
    public void setUp() {
        when(ingredientMapper.toDTO(any())).thenReturn(null);
        when(ingredientMapper.toEntity(any())).thenReturn(null);
    }

    @Test
    public void shouldReturnMealIngredientDTOWhenMapMealIngredientEntity() {
        // given
        MealIngredient mealIngredientEntity = MealIngredientGenerator.getSampleMealIngredientEntity();
        MealIngredientDTO mealIngredientDTO = MealIngredientGenerator.getSampleMealIngredientDTO();

        // when
        MealIngredientDTO returnedMealIngredientDTO = mealIngredientMapper.toDTO(mealIngredientEntity);

        // then
        assertEquals(mealIngredientDTO, returnedMealIngredientDTO);
    }

    @Test
    public void shouldReturnMealIngredientEntityWhenMapMealIngredientDTO() {
        // given
        MealIngredient mealIngredientEntity = MealIngredientGenerator.getSampleMealIngredientEntity();
        MealIngredientDTO mealIngredientDTO = MealIngredientGenerator.getSampleMealIngredientDTO();

        // when
        MealIngredient returnedMealIngredientEntity = mealIngredientMapper.toEntity(mealIngredientDTO, null);

        // then
        assertEquals(mealIngredientEntity, returnedMealIngredientEntity);
    }

    @Test
    public void shouldReturnMealIngredientEntityWithMealWhenMapMealIngredientDTOWithMealId() {
        // given
        Meal meal = MealGenerator.getSampleMealEntity();
        meal.setId(5L);
        MealIngredient mealIngredientEntity = MealIngredientGenerator.getSampleMealIngredientEntity();
        mealIngredientEntity.setMeal(meal);
        MealIngredientDTO mealIngredientDTO = MealIngredientGenerator.getSampleMealIngredientDTO();
        when(mealRepository.findById(any())).thenReturn(Optional.of(meal));

        // when
        MealIngredient returnedMealIngredientEntity = mealIngredientMapper.toEntity(mealIngredientDTO, meal.getId());

        // then
        assertEquals(mealIngredientEntity, returnedMealIngredientEntity);
    }

    @Test
    public void shouldReturnMealIngredientEntityWithNullsWhenMapMealIngredientDTOWithNulls() {
        // given
        MealIngredient mealIngredientEntity = new MealIngredient();
        MealIngredientDTO mealIngredientDTO = new MealIngredientDTO();

        // when
        MealIngredient returnedMealIngredientEntity = mealIngredientMapper.toEntity(mealIngredientDTO, null);

        // then
        assertEquals(mealIngredientEntity, returnedMealIngredientEntity);
    }

    @Test
    public void shouldReturnMealIngredientDtoWithNullsWhenMapMealIngredientEntityWithNulls() {
        // given
        MealIngredient mealIngredientEntity = new MealIngredient();
        MealIngredientDTO mealIngredientDTO = new MealIngredientDTO();

        // when
        MealIngredientDTO returnedMealIngredientDto = mealIngredientMapper.toDTO(mealIngredientEntity);

        // then
        assertEquals(mealIngredientDTO, returnedMealIngredientDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        MealIngredient returnedMealIngredientEntity = mealIngredientMapper.toEntity(null, null);

        // then
        assertNull(returnedMealIngredientEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        MealIngredientDTO returnedMealIngredientDto = mealIngredientMapper.toDTO(null);

        // then
        assertNull(returnedMealIngredientDto);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyMealIngredientDTOList() {
        // given
        // when
        List<MealIngredient> returnedMealIngredientEntityList =
                mealIngredientMapper.toEntities(new ArrayList<>(), null);

        // then
        assertEquals(new ArrayList<>(), returnedMealIngredientEntityList);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyMealIngredientEntityList() {
        // given
        // when
        List<MealIngredientDTO> returnedMealIngredientDtoList = mealIngredientMapper.toDTOs(new ArrayList<>());

        // then
        assertEquals(new ArrayList<>(), returnedMealIngredientDtoList);
    }

    @Test
    public void shouldReturnNullWhenMapEntityListWhichIsNull() {
        // given
        // when
        List<MealIngredient> returnedMealIngredientEntityList = mealIngredientMapper.toEntities(null, null);

        // then
        assertNull(returnedMealIngredientEntityList);
    }

    @Test
    public void shouldReturnNullWhenMapDtoListWhichIsNull() {
        // given
        // when
        List<MealIngredientDTO> returnedMealIngredientDtoList = mealIngredientMapper.toDTOs(null);

        // then
        assertNull(returnedMealIngredientDtoList);
    }
}