package pl.fit_amam.api.mappers;

import pl.fit_amam.api.base.ShoppingElementGenerator;
import pl.fit_amam.api.base.ShoppingListGenerator;
import pl.fit_amam.api.dto.ShoppingElementDTO;
import pl.fit_amam.api.persistence.entities.ShoppingElement;
import pl.fit_amam.api.persistence.entities.ShoppingList;
import pl.fit_amam.api.persistence.repositories.ShoppingListRepository;
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
public class ShoppingElementMapperTest {
    @InjectMocks
    private ShoppingElementMapper shoppingElementMapper;

    @Mock
    private IngredientMapper ingredientMapper;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Before
    public void setUp() {
        when(ingredientMapper.toDTO(any())).thenReturn(null);
        when(ingredientMapper.toEntity(any())).thenReturn(null);
    }

    @Test
    public void shouldReturnShoppingElementDTOWhenMapShoppingElementEntity() {
        // given
        ShoppingElement shoppingElementEntity = ShoppingElementGenerator.getSampleShoppingElementEntity();
        ShoppingElementDTO shoppingElementDTO = ShoppingElementGenerator.getSampleShoppingElementDTO();

        // when
        ShoppingElementDTO returnedShoppingElementDTO = shoppingElementMapper.toDTO(shoppingElementEntity);

        // then
        assertEquals(shoppingElementDTO, returnedShoppingElementDTO);
    }

    @Test
    public void shouldReturnShoppingElementEntityWhenMapShoppingElementDTO() {
        // given
        ShoppingElement shoppingElementEntity = ShoppingElementGenerator.getSampleShoppingElementEntity();
        ShoppingElementDTO shoppingElementDTO = ShoppingElementGenerator.getSampleShoppingElementDTO();

        // when
        ShoppingElement returnedShoppingElementEntity = shoppingElementMapper.toEntity(shoppingElementDTO, null);

        // then
        assertEquals(shoppingElementEntity, returnedShoppingElementEntity);
    }

    @Test
    public void shouldReturnShoppingElementEntityWithMenuWhenMapShoppingElementDTOWithMenuId() {
        // given
        ShoppingList shoppingList = ShoppingListGenerator.getSampleShoppingListEntity();
        shoppingList.setId(5L);
        ShoppingElement shoppingElementEntity = ShoppingElementGenerator.getSampleShoppingElementEntity();
        shoppingElementEntity.setShoppingList(shoppingList);
        ShoppingElementDTO shoppingElementDTO = ShoppingElementGenerator.getSampleShoppingElementDTO();
        when(shoppingListRepository.findById(any())).thenReturn(Optional.of(shoppingList));

        // when
        ShoppingElement returnedShoppingElementEntity = shoppingElementMapper.toEntity(shoppingElementDTO, shoppingList.getId());

        // then
        assertEquals(shoppingElementEntity, returnedShoppingElementEntity);
    }

    @Test
    public void shouldReturnShoppingElementEntityWithNullsWhenMapShoppingElementDTOWithNulls() {
        // given
        ShoppingElement shoppingElementEntity = new ShoppingElement();
        ShoppingElementDTO shoppingElementDTO = new ShoppingElementDTO();

        // when
        ShoppingElement returnedShoppingElementEntity = shoppingElementMapper.toEntity(shoppingElementDTO, null);

        // then
        assertEquals(shoppingElementEntity, returnedShoppingElementEntity);
    }

    @Test
    public void shouldReturnShoppingElementDtoWithNullsWhenMapShoppingElementEntityWithNulls() {
        // given
        ShoppingElement shoppingElementEntity = new ShoppingElement();
        ShoppingElementDTO shoppingElementDTO = new ShoppingElementDTO();

        // when
        ShoppingElementDTO returnedShoppingElementDto = shoppingElementMapper.toDTO(shoppingElementEntity);

        // then
        assertEquals(shoppingElementDTO, returnedShoppingElementDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        ShoppingElement returnedShoppingElementEntity = shoppingElementMapper.toEntity(null, null);

        // then
        assertNull(returnedShoppingElementEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        ShoppingElementDTO returnedShoppingElementDto = shoppingElementMapper.toDTO(null);

        // then
        assertNull(returnedShoppingElementDto);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyShoppingElementDTOList() {
        // given
        // when
        List<ShoppingElement> returnedShoppingElementEntityList =
                shoppingElementMapper.toEntities(new ArrayList<>(), null);

        // then
        assertEquals(new ArrayList<>(), returnedShoppingElementEntityList);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyShoppingElementEntityList() {
        // given
        // when
        List<ShoppingElementDTO> returnedShoppingElementDtoList = shoppingElementMapper.toDTOs(new ArrayList<>());

        // then
        assertEquals(new ArrayList<>(), returnedShoppingElementDtoList);
    }

    @Test
    public void shouldReturnNullWhenMapEntityListWhichIsNull() {
        // given
        // when
        List<ShoppingElement> returnedShoppingElementEntityList = shoppingElementMapper.toEntities(null, null);

        // then
        assertNull(returnedShoppingElementEntityList);
    }

    @Test
    public void shouldReturnNullWhenMapDtoListWhichIsNull() {
        // given
        // when
        List<ShoppingElementDTO> returnedShoppingElementDtoList = shoppingElementMapper.toDTOs(null);

        // then
        assertNull(returnedShoppingElementDtoList);
    }
}