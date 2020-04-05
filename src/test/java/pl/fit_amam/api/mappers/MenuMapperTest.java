package pl.fit_amam.api.mappers;

import pl.fit_amam.api.base.MenuGenerator;
import pl.fit_amam.api.dto.MenuDTO;
import pl.fit_amam.api.persistence.entities.Menu;
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
public class MenuMapperTest {
    @InjectMocks
    private MenuMapper menuMapper;

    @Mock
    private MenuMealMapper menuMealMapper;

    @Before
    public void setUp() {
        when(menuMealMapper.toDTOs(any())).thenReturn(null);
        when(menuMealMapper.toEntities(any(), any())).thenReturn(null);
    }

    @Test
    public void shouldReturnMenuDTOWhenMapMenuEntity() {
        // given
        Menu menuEntity = MenuGenerator.getSampleMenuEntity();
        MenuDTO menuDTO = MenuGenerator.getSampleMenuDTO();

        // when
        MenuDTO returnedMenuDTO = menuMapper.toDTO(menuEntity);

        // then
        assertEquals(menuDTO, returnedMenuDTO);
    }

    @Test
    public void shouldReturnMenuEntityWhenMapMenuDTO() {
        // given
        Menu menuEntity = MenuGenerator.getSampleMenuEntity();
        MenuDTO menuDTO = MenuGenerator.getSampleMenuDTO();

        // when
        Menu returnedMenuEntity = menuMapper.toEntity(menuDTO);

        // then
        assertEquals(menuEntity, returnedMenuEntity);
    }

    @Test
    public void shouldReturnMenuEntityWithNullsWhenMapMenuDTOWithNulls() {
        // given
        Menu menuEntity = new Menu();
        MenuDTO menuDTO = new MenuDTO();

        // when
        Menu returnedMenuEntity = menuMapper.toEntity(menuDTO);

        // then
        assertEquals(menuEntity, returnedMenuEntity);
    }

    @Test
    public void shouldReturnMenuDtoWithNullsWhenMapMenuEntityWithNulls() {
        // given
        Menu menuEntity = new Menu();
        MenuDTO menuDTO = new MenuDTO();

        // when
        MenuDTO returnedMenuDto = menuMapper.toDTO(menuEntity);

        // then
        assertEquals(menuDTO, returnedMenuDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        Menu returnedMenuEntity = menuMapper.toEntity(null);

        // then
        assertNull(returnedMenuEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        MenuDTO returnedMenuDto = menuMapper.toDTO(null);

        // then
        assertNull(returnedMenuDto);
    }
}