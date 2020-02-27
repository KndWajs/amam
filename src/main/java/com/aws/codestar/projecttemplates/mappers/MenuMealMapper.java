package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.MenuMealDTO;
import com.aws.codestar.projecttemplates.persistence.entities.MenuMeal;
import com.aws.codestar.projecttemplates.persistence.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuMealMapper  {
    private MealMapper mealMapper;
    private MenuRepository menuRepository;

    @Autowired
    public MenuMealMapper(MealMapper mealMapper, MenuRepository menuRepository) {
        this.mealMapper = mealMapper;
        this.menuRepository = menuRepository;
    }

    public MenuMealDTO toDTO(MenuMeal menuMeal) {
        return MenuMealDTO.builder()
                .id(menuMeal.getId())
                .meal(mealMapper.toDTO(menuMeal.getMeal()))
                .dayNumber(menuMeal.getDayNumber())
                .build();
    }

    public MenuMeal toEntity(MenuMealDTO menuMealDto, Long menuId) {
        return MenuMeal.builder()
                .id(menuMealDto.getId())
                .menu(menuId == null ? null : menuRepository.findById(menuId).get())
                .meal(mealMapper.toEntity(menuMealDto.getMeal()))
                .dayNumber(menuMealDto.getDayNumber())
                .build();
    }

    public List<MenuMealDTO> toDTOs(List<MenuMeal> menuMeals) {
        return menuMeals.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<MenuMeal> toEntities(List<MenuMealDTO> menuMealsDto, Long mealId) {
        return menuMealsDto.stream().map(dto -> toEntity(dto, mealId)).collect(Collectors.toList());
    }

}

