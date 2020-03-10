package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.base.IngredientCategoryGenerator;
import com.aws.codestar.projecttemplates.dto.IngredientCategoryDTO;
import com.aws.codestar.projecttemplates.persistence.entities.IngredientCategory;
import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientCategoryMapperTest {
    @InjectMocks
    private IngredientCategoryMapper ingredientCategoryMapper;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldReturnIngredientCategoryDTOWhenMapIngredientCategoryEntity() {
        // given
        IngredientCategory ingredientCategoryEntity = IngredientCategoryGenerator.getSampleIngredientCategoryEntity();
        IngredientCategoryDTO ingredientCategoryDTO = IngredientCategoryGenerator.getSampleIngredientCategoryDTO();

        // when
        IngredientCategoryDTO returnedIngredientCategoryDTO = ingredientCategoryMapper.toDTO(ingredientCategoryEntity);

        // then
        assertEquals(ingredientCategoryDTO, returnedIngredientCategoryDTO);
    }

    @Test
    public void shouldReturnIngredientCategoryEntityWhenMapIngredientCategoryDTO() {
        // given
        IngredientCategory ingredientCategoryEntity = IngredientCategoryGenerator.getSampleIngredientCategoryEntity();
        IngredientCategoryDTO ingredientCategoryDTO = IngredientCategoryGenerator.getSampleIngredientCategoryDTO();

        // when
        IngredientCategory returnedIngredientCategoryEntity = ingredientCategoryMapper.toEntity(ingredientCategoryDTO);

        // then
        assertEquals(ingredientCategoryEntity, returnedIngredientCategoryEntity);
    }

    @Test
    public void shouldReturnIngredientCategoryEntityWithNullsWhenMapIngredientCategoryDTOWithNulls() {
        // given
        IngredientCategory ingredientCategoryEntity = new IngredientCategory();
        IngredientCategoryDTO ingredientCategoryDTO = new IngredientCategoryDTO();

        // when
        IngredientCategory returnedIngredientCategoryEntity = ingredientCategoryMapper.toEntity(ingredientCategoryDTO);

        // then
        assertEquals(ingredientCategoryEntity, returnedIngredientCategoryEntity);
    }

    @Test
    public void shouldReturnIngredientCategoryDtoWithNullsWhenMapIngredientCategoryEntityWithNulls() {
        // given
        IngredientCategory ingredientCategoryEntity = new IngredientCategory();
        IngredientCategoryDTO ingredientCategoryDTO = new IngredientCategoryDTO();

        // when
        IngredientCategoryDTO returnedIngredientCategoryDto = ingredientCategoryMapper.toDTO(ingredientCategoryEntity);

        // then
        assertEquals(ingredientCategoryDTO, returnedIngredientCategoryDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        IngredientCategory returnedIngredientCategoryEntity = ingredientCategoryMapper.toEntity(null);

        // then
        assertNull(returnedIngredientCategoryEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        IngredientCategoryDTO returnedIngredientCategoryDto = ingredientCategoryMapper.toDTO(null);

        // then
        assertNull(returnedIngredientCategoryDto);
    }
}