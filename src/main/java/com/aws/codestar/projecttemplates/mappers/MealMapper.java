package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper implements Mapper<MealDTO, Meal> {

    private MealIngredientMapper mealIngredientMapper;

    @Autowired
    public MealMapper(MealIngredientMapper mealIngredientMapper) {
        this.mealIngredientMapper = mealIngredientMapper;
    }

    @Override
    public MealDTO toDTO(Meal meal) {
        if (meal == null) {
            return null;
        }

        return MealDTO.builder()
                .id(meal.getId())
                .name(meal.getName())
                .typeOfMeal(meal.getTypeOfMeal())
                .typeOfPreparing(meal.getTypeOfPreparing())
                .recipe(meal.getRecipe())
                .minutesToPrepare(meal.getMinutesToPrepare())
                .ingredients(mealIngredientMapper.toDTOs(meal.getMealIngredients()))
                .userName(meal.getUserName())
                .creationDate(meal.getCreationDate())
                .updateDate(meal.getUpdateDate())
                .build();
    }

    @Override
    public Meal toEntity(MealDTO meal) {
        if (meal == null) {
            return null;
        }

        return Meal.builder()
                .id(meal.getId())
                .name(meal.getName() == null? null : meal.getName().toLowerCase())
                .typeOfMeal(meal.getTypeOfMeal())
                .typeOfPreparing(meal.getTypeOfPreparing())
                .recipe(meal.getRecipe())
                .minutesToPrepare(meal.getMinutesToPrepare())
                .mealIngredients(mealIngredientMapper.toEntities(meal.getIngredients(), meal.getId()))
                .userName(meal.getUserName())
                .creationDate(meal.getCreationDate())
                .updateDate(meal.getUpdateDate())
                .build();
    }

    public List<MealDTO> toDTOs(List<Meal> meals) {
        if (meals == null) {
            return null;
        }

        return meals.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<Meal> toEntities(List<MealDTO> meals) {
        if (meals == null) {
            return null;
        }

        return meals.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

}

