package pl.fit_amam.api.mappers;

import pl.fit_amam.api.base.MealGenerator;
import pl.fit_amam.api.dto.MealDTO;
import pl.fit_amam.api.persistence.entities.Meal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealMapperTest {
    @InjectMocks
    private MealMapper mealMapper;

    @Mock
    private MealIngredientMapper mealIngredientMapper;

    @Before
    public void setUp() {
        when(mealIngredientMapper.toDTOs(any())).thenReturn(null);
        when(mealIngredientMapper.toEntities(any(), any())).thenReturn(null);
    }

    @Test
    public void shouldReturnMealDTOWhenMapMealEntity() {
        // given
        Meal mealEntity = MealGenerator.getSampleMealEntity();
        MealDTO mealDTO = MealGenerator.getSampleMealDTO();

        // when
        MealDTO returnedMealDTO = mealMapper.toDTO(mealEntity);

        // then
        assertEquals(mealDTO, returnedMealDTO);
    }

    @Test
    public void shouldReturnMealEntityWhenMapMealDTO() {
        // given
        Meal mealEntity = MealGenerator.getSampleMealEntity();
        MealDTO mealDTO = MealGenerator.getSampleMealDTO();

        // when
        Meal returnedMealEntity = mealMapper.toEntity(mealDTO);

        // then
        assertEquals(mealEntity, returnedMealEntity);
    }

    @Test
    public void shouldReturnMealEntityWithNullsWhenMapMealDTOWithNulls() {
        // given
        Meal mealEntity = new Meal();
        MealDTO mealDTO = new MealDTO();

        // when
        Meal returnedMealEntity = mealMapper.toEntity(mealDTO);

        // then
        assertEquals(mealEntity, returnedMealEntity);
    }

    @Test
    public void shouldReturnMealDtoWithNullsWhenMapMealEntityWithNulls() {
        // given
        Meal mealEntity = new Meal();
        MealDTO mealDTO = new MealDTO();

        // when
        MealDTO returnedMealDto = mealMapper.toDTO(mealEntity);

        // then
        assertEquals(mealDTO, returnedMealDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        Meal returnedMealEntity = mealMapper.toEntity(null);

        // then
        assertNull(returnedMealEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        MealDTO returnedMealDto = mealMapper.toDTO(null);

        // then
        assertNull(returnedMealDto);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyMealDTOList() {
        // given
        // when
        List<Meal> returnedMealEntityList = mealMapper.toEntities(new ArrayList<>());

        // then
        assertEquals(new ArrayList<>(), returnedMealEntityList);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyMealEntityList() {
        // given
        // when
        List<MealDTO> returnedMealDtoList = mealMapper.toDTOs(new ArrayList<>());

        // then
        assertEquals(new ArrayList<>(), returnedMealDtoList);
    }

    @Test
    public void shouldReturnNullWhenMapEntityListWhichIsNull() {
        // given
        // when
        List<Meal> returnedMealEntityList = mealMapper.toEntities(null);

        // then
        assertNull(returnedMealEntityList);
    }

    @Test
    public void shouldReturnNullWhenMapDtoListWhichIsNull() {
        // given
        // when
        List<MealDTO> returnedMealDtoList = mealMapper.toDTOs(null);

        // then
        assertNull(returnedMealDtoList);
    }
}