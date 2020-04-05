package pl.fit_amam.api.base;

import pl.fit_amam.api.dto.MenuMealDTO;
import pl.fit_amam.api.persistence.entities.MenuMeal;

import java.util.ArrayList;
import java.util.List;

public class MenuMealGenerator {

    public static MenuMeal getSampleMenuMealEntity() {
        return MenuMeal.builder()
                .dayNumber(1)
                .build();
    }

    public static MenuMealDTO getSampleMenuMealDTO() {
        return MenuMealDTO.builder()
                .dayNumber(1)
                .build();
    }

    public static List<MenuMeal> getSampleMenuMealEntities() {
        List menuMeals = new ArrayList();
        menuMeals.add(getSampleMenuMealEntity());
        return menuMeals;
    }

    public static List<MenuMealDTO> getSampleMenuMealDTOs() {
        List menuMeals = new ArrayList();
        menuMeals.add(getSampleMenuMealDTO());
        return menuMeals;
    }
}
