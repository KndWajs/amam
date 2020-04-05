package pl.fit_amam.api.mappers;

import pl.fit_amam.api.base.IngredientCategoryGenerator;
import pl.fit_amam.api.dto.IngredientCategoryDTO;
import pl.fit_amam.api.persistence.entities.IngredientCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;

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