package pl.fit_amam.api.mappers;

import pl.fit_amam.api.base.ShoppingListGenerator;
import pl.fit_amam.api.dto.ShoppingListDTO;
import pl.fit_amam.api.persistence.entities.ShoppingList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListMapperTest {
    @InjectMocks
    private ShoppingListMapper shoppingListMapper;

    @Mock
    private ShoppingElementMapper shoppingElementMapper;

    @Before
    public void setUp() {
        when(shoppingElementMapper.toDTOs(any())).thenReturn(null);
        when(shoppingElementMapper.toEntities(any(), any())).thenReturn(null);
    }

    @Test
    public void shouldReturnShoppingListDTOWhenMapShoppingListEntity() {
        // given
        ShoppingList shoppingListEntity = ShoppingListGenerator.getSampleShoppingListEntity();
        ShoppingListDTO shoppingListDTO = ShoppingListGenerator.getSampleShoppingListDTO();

        // when
        ShoppingListDTO returnedShoppingListDTO = shoppingListMapper.toDTO(shoppingListEntity);

        // then
        assertEquals(shoppingListDTO, returnedShoppingListDTO);
    }

    @Test
    public void shouldReturnShoppingListEntityWhenMapShoppingListDTO() {
        // given
        ShoppingList shoppingListEntity = ShoppingListGenerator.getSampleShoppingListEntity();
        ShoppingListDTO shoppingListDTO = ShoppingListGenerator.getSampleShoppingListDTO();

        // when
        ShoppingList returnedShoppingListEntity = shoppingListMapper.toEntity(shoppingListDTO);

        // then
        assertEquals(shoppingListEntity, returnedShoppingListEntity);
    }

    @Test
    public void shouldReturnShoppingListEntityWithNullsWhenMapShoppingListDTOWithNulls() {
        // given
        ShoppingList shoppingListEntity = new ShoppingList();
        ShoppingListDTO shoppingListDTO = new ShoppingListDTO();

        // when
        ShoppingList returnedShoppingListEntity = shoppingListMapper.toEntity(shoppingListDTO);

        // then
        assertEquals(shoppingListEntity, returnedShoppingListEntity);
    }

    @Test
    public void shouldReturnShoppingListDtoWithNullsWhenMapShoppingListEntityWithNulls() {
        // given
        ShoppingList shoppingListEntity = new ShoppingList();
        ShoppingListDTO shoppingListDTO = new ShoppingListDTO();

        // when
        ShoppingListDTO returnedShoppingListDto = shoppingListMapper.toDTO(shoppingListEntity);

        // then
        assertEquals(shoppingListDTO, returnedShoppingListDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        ShoppingList returnedShoppingListEntity = shoppingListMapper.toEntity(null);

        // then
        assertNull(returnedShoppingListEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        ShoppingListDTO returnedShoppingListDto = shoppingListMapper.toDTO(null);

        // then
        assertNull(returnedShoppingListDto);
    }
}