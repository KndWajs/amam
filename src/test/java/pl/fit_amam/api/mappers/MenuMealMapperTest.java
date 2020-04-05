package pl.fit_amam.api.mappers;

import pl.fit_amam.api.base.MenuGenerator;
import pl.fit_amam.api.base.MenuMealGenerator;
import pl.fit_amam.api.dto.MenuMealDTO;
import pl.fit_amam.api.persistence.entities.Menu;
import pl.fit_amam.api.persistence.entities.MenuMeal;
import pl.fit_amam.api.persistence.repositories.MenuRepository;
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
public class MenuMealMapperTest {
    @InjectMocks
    private MenuMealMapper menuMealMapper;

    @Mock
    private MealMapper mealMapper;

    @Mock
    private MenuRepository menuRepository;

    @Before
    public void setUp() {
        when(mealMapper.toDTO(any())).thenReturn(null);
        when(mealMapper.toEntity(any())).thenReturn(null);
    }

    @Test
    public void shouldReturnMenuMealDTOWhenMapMenuMealEntity() {
        // given
        MenuMeal menuMealEntity = MenuMealGenerator.getSampleMenuMealEntity();
        MenuMealDTO menuMealDTO = MenuMealGenerator.getSampleMenuMealDTO();

        // when
        MenuMealDTO returnedMenuMealDTO = menuMealMapper.toDTO(menuMealEntity);

        // then
        assertEquals(menuMealDTO, returnedMenuMealDTO);
    }

    @Test
    public void shouldReturnMenuMealEntityWhenMapMenuMealDTO() {
        // given
        MenuMeal menuMealEntity = MenuMealGenerator.getSampleMenuMealEntity();
        MenuMealDTO menuMealDTO = MenuMealGenerator.getSampleMenuMealDTO();

        // when
        MenuMeal returnedMenuMealEntity = menuMealMapper.toEntity(menuMealDTO, null);

        // then
        assertEquals(menuMealEntity, returnedMenuMealEntity);
    }

    @Test
    public void shouldReturnMenuMealEntityWithMenuWhenMapMenuMealDTOWithMenuId() {
        // given
        Menu menu = MenuGenerator.getSampleMenuEntity();
        menu.setId(5L);
        MenuMeal menuMealEntity = MenuMealGenerator.getSampleMenuMealEntity();
        menuMealEntity.setMenu(menu);
        MenuMealDTO menuMealDTO = MenuMealGenerator.getSampleMenuMealDTO();
        when(menuRepository.findById(any())).thenReturn(Optional.of(menu));

        // when
        MenuMeal returnedMenuMealEntity = menuMealMapper.toEntity(menuMealDTO, menu.getId());

        // then
        assertEquals(menuMealEntity, returnedMenuMealEntity);
    }

    @Test
    public void shouldReturnMenuMealEntityWithNullsWhenMapMenuMealDTOWithNulls() {
        // given
        MenuMeal menuMealEntity = new MenuMeal();
        MenuMealDTO menuMealDTO = new MenuMealDTO();

        // when
        MenuMeal returnedMenuMealEntity = menuMealMapper.toEntity(menuMealDTO, null);

        // then
        assertEquals(menuMealEntity, returnedMenuMealEntity);
    }

    @Test
    public void shouldReturnMenuMealDtoWithNullsWhenMapMenuMealEntityWithNulls() {
        // given
        MenuMeal menuMealEntity = new MenuMeal();
        MenuMealDTO menuMealDTO = new MenuMealDTO();

        // when
        MenuMealDTO returnedMenuMealDto = menuMealMapper.toDTO(menuMealEntity);

        // then
        assertEquals(menuMealDTO, returnedMenuMealDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        MenuMeal returnedMenuMealEntity = menuMealMapper.toEntity(null, null);

        // then
        assertNull(returnedMenuMealEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        MenuMealDTO returnedMenuMealDto = menuMealMapper.toDTO(null);

        // then
        assertNull(returnedMenuMealDto);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyMenuMealDTOList() {
        // given
        // when
        List<MenuMeal> returnedMenuMealEntityList =
                menuMealMapper.toEntities(new ArrayList<>(), null);

        // then
        assertEquals(new ArrayList<>(), returnedMenuMealEntityList);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyMenuMealEntityList() {
        // given
        // when
        List<MenuMealDTO> returnedMenuMealDtoList = menuMealMapper.toDTOs(new ArrayList<>());

        // then
        assertEquals(new ArrayList<>(), returnedMenuMealDtoList);
    }

    @Test
    public void shouldReturnNullWhenMapEntityListWhichIsNull() {
        // given
        // when
        List<MenuMeal> returnedMenuMealEntityList = menuMealMapper.toEntities(null, null);

        // then
        assertNull(returnedMenuMealEntityList);
    }

    @Test
    public void shouldReturnNullWhenMapDtoListWhichIsNull() {
        // given
        // when
        List<MenuMealDTO> returnedMenuMealDtoList = menuMealMapper.toDTOs(null);

        // then
        assertNull(returnedMenuMealDtoList);
    }
}