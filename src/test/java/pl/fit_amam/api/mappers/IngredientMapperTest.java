package pl.fit_amam.api.mappers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.fit_amam.api.base.IngredientGenerator;
import pl.fit_amam.api.dto.IngredientDTO;
import pl.fit_amam.api.persistence.entities.Ingredient;
import pl.fit_amam.api.persistence.repositories.MealIngredientDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class IngredientMapperTest {
    @InjectMocks
    private IngredientMapper ingredientMapper;

    @Mock
    private MealIngredientDao mealIngredientDao;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldReturnIngredientDTOWhenMapIngredientEntity() {
        // given
        Ingredient ingredientEntity = IngredientGenerator.getSampleIngredientEntity();
        IngredientDTO ingredientDTO = IngredientGenerator.getSampleIngredientDTO();

        // when
        IngredientDTO returnedIngredientDTO = ingredientMapper.toDTO(ingredientEntity);

        // then
        assertEquals(ingredientDTO, returnedIngredientDTO);
    }

    @Test
    public void shouldReturnIngredientEntityWhenMapIngredientDTO() {
        // given
        Ingredient ingredientEntity = IngredientGenerator.getSampleIngredientEntity();
        IngredientDTO ingredientDTO = IngredientGenerator.getSampleIngredientDTO();

        // when
        Ingredient returnedIngredientEntity = ingredientMapper.toEntity(ingredientDTO);

        // then
        assertEquals(ingredientEntity, returnedIngredientEntity);
    }

    @Test
    public void shouldReturnIngredientEntityWithNullsWhenMapIngredientDTOWithNulls() {
        // given
        Ingredient ingredientEntity = new Ingredient();
        IngredientDTO ingredientDTO = new IngredientDTO();

        // when
        Ingredient returnedIngredientEntity = ingredientMapper.toEntity(ingredientDTO);

        // then
        assertEquals(ingredientEntity, returnedIngredientEntity);
    }

    @Test
    public void shouldReturnIngredientDtoWithNullsWhenMapIngredientEntityWithNulls() {
        // given
        Ingredient ingredientEntity = new Ingredient();
        IngredientDTO ingredientDTO = new IngredientDTO();

        // when
        IngredientDTO returnedIngredientDto = ingredientMapper.toDTO(ingredientEntity);

        // then
        assertEquals(ingredientDTO, returnedIngredientDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        Ingredient returnedIngredientEntity = ingredientMapper.toEntity(null);

        // then
        assertNull(returnedIngredientEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        IngredientDTO returnedIngredientDto = ingredientMapper.toDTO(null);

        // then
        assertNull(returnedIngredientDto);
    }
}