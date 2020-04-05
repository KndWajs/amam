package pl.fit_amam.api.mappers;

import pl.fit_amam.api.dto.MenuMealDTO;
import pl.fit_amam.api.persistence.entities.MenuMeal;
import pl.fit_amam.api.persistence.repositories.MenuRepository;
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
        if (menuMeal == null) {
            return null;
        }

        return MenuMealDTO.builder()
                .id(menuMeal.getId())
                .meal(mealMapper.toDTO(menuMeal.getMeal()))
                .dayNumber(menuMeal.getDayNumber())
                .build();
    }

    public MenuMeal toEntity(MenuMealDTO menuMeal, Long menuId) {
        if (menuMeal == null) {
            return null;
        }

        return MenuMeal.builder()
                .id(menuMeal.getId())
                .menu(menuId == null ? null : menuRepository.findById(menuId).get())
                .meal(mealMapper.toEntity(menuMeal.getMeal()))
                .dayNumber(menuMeal.getDayNumber())
                .build();
    }

    public List<MenuMealDTO> toDTOs(List<MenuMeal> menuMeals) {
        if (menuMeals == null) {
            return null;
        }

        return menuMeals.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<MenuMeal> toEntities(List<MenuMealDTO> menuMeals, Long mealId) {
        if (menuMeals == null) {
            return null;
        }

        return menuMeals.stream().map(dto -> toEntity(dto, mealId)).collect(Collectors.toList());
    }

}

