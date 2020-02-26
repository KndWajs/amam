package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.MenuMealDTO;
import com.aws.codestar.projecttemplates.persistence.entities.MenuMeal;

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
