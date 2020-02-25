package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.base.MealGenerator;
import com.aws.codestar.projecttemplates.base.MealIngredientGenerator;
import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class MealMapperTest {
    @InjectMocks
    private MealMapper mealMapper;

    @Mock
    private MealIngredientMapper mealIngredientMapper;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldReturnMealDTOWhenMapMealEntity() {
        // given
        Meal mealEntity = MealGenerator.getSampleMealEntity();
        mealEntity.setMealIngredients(MealIngredientGenerator.getSampleMealIngredientEntities());
        MealDTO mealDTO = MealGenerator.getSampleMealDTO();
        mealDTO.setIngredients(MealIngredientGenerator.getSampleMealIngredientDTOs());

        when(mealIngredientMapper.toDTOs(any())).thenReturn(MealIngredientGenerator.getSampleMealIngredientDTOs());

        // when
        MealDTO returnedMealDTO = mealMapper.toDTO(mealEntity);

        // then
        assertEquals(mealDTO, returnedMealDTO);
    }

    @Test
    public void shouldReturnMealEntityWhenMapMealDTO() {
        // given
        Meal mealEntity = MealGenerator.getSampleMealEntity();
        mealEntity.setMealIngredients(MealIngredientGenerator.getSampleMealIngredientEntities());
        MealDTO mealDTO = MealGenerator.getSampleMealDTO();
        mealDTO.setIngredients(MealIngredientGenerator.getSampleMealIngredientDTOs());

        when(mealIngredientMapper.toEntities(any(), any())).thenReturn(MealIngredientGenerator.getSampleMealIngredientEntities());

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
        Meal mealEntity = null;
        MealDTO mealDTO = null;

        // when
        Meal returnedMealEntity = mealMapper.toEntity(mealDTO);

        // then
        assertEquals(mealEntity, returnedMealEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        Meal mealEntity = null;
        MealDTO mealDTO = null;

        // when
        MealDTO returnedMealDto = mealMapper.toDTO(mealEntity);

        // then
        assertEquals(mealDTO, returnedMealDto);
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
        assertEquals(null, returnedMealEntityList);
    }

    @Test
    public void shouldReturnNullWhenMapDtoListWhichIsNull() {
        // given
        // when
        List<MealDTO> returnedMealDtoList = mealMapper.toDTOs(null);

        // then
        assertEquals(null, returnedMealDtoList);
    }
}