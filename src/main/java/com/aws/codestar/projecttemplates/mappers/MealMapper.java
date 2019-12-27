package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dao.MealIngredientDao;
import com.aws.codestar.projecttemplates.dto.MealDto;
import com.aws.codestar.projecttemplates.entities.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper implements Mapper<MealDto, Meal> {

    private MealIngredientMapper mealIngredientMapper;
    private MealIngredientDao mealIngredientDao;

    @Autowired
    public MealMapper(MealIngredientMapper mealIngredientMapper, MealIngredientDao mealIngredientDao) {
        this.mealIngredientMapper = mealIngredientMapper;
        this.mealIngredientDao = mealIngredientDao;
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
    public Meal toEntity(MealDto mealDto) {
        return Meal.builder()
                .id(mealDto.getId())
                .name(mealDto.getName())
                .typeOfMeal(mealDto.getTypeOfMeal())
                .typeOfPreparing(mealDto.getTypeOfPreparing())
                .recipe(mealDto.getRecipe())
                .minutesToPrepare(mealDto.getMinutesToPrepare())
                .mealIngredients(mealIngredientDao.getMealIngredientsByMealId(mealDto.getId()))
                .build();
    }

    public List<MealDto> toDtos(List<Meal> meals) {
        return meals.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }

    public List<Meal> toEntities(List<MealDto> mealsDto) {
        return mealsDto.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

}

