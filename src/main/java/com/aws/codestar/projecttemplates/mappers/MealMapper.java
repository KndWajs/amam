package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.MealDto;
import com.aws.codestar.projecttemplates.entities.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper implements Mapper<MealDto, Meal> {

    private MealIngredientMapper mealIngredientMapper;

    @Autowired
    public MealMapper(MealIngredientMapper mealIngredientMapper) {
        this.mealIngredientMapper = mealIngredientMapper;
    }

    @Override
    public MealDto toDto(Meal Meal) {
        return MealDto.builder()
                .id(Meal.getId())
                .name(Meal.getName())
                .typeOfMeal(Meal.getTypeOfMeal())
                .typeOfPreparing(Meal.getTypeOfPreparing())
                .recipe(Meal.getRecipe())
                .minutesToPrepare(Meal.getMinutesToPrepare())
                .ingredients(mealIngredientMapper.toDtos(Meal.getMealIngredients()))
                .build();
    }

    @Override
    public Meal toEntity(MealDto MealDto) {
        return Meal.builder()
                .id(MealDto.getId())
                .name(MealDto.getName())
                .typeOfMeal(MealDto.getTypeOfMeal())
                .typeOfPreparing(MealDto.getTypeOfPreparing())
                .recipe(MealDto.getRecipe())
                .minutesToPrepare(MealDto.getMinutesToPrepare())
                .mealIngredients(mealIngredientMapper.toEntities(MealDto.getIngredients()))
                .build();
    }

    public List<MealDto> toDtos(List<Meal> meals) {
        return meals.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }

    public List<Meal> toEntities(List<MealDto> mealsDto) {
        return mealsDto.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

}

