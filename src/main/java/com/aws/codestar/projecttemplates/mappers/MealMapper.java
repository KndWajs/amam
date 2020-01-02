package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientDao;
import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper implements Mapper<MealDTO, Meal> {

    private MealIngredientMapper mealIngredientMapper;
    private MealIngredientDao mealIngredientDao;

    @Autowired
    public MealMapper(MealIngredientMapper mealIngredientMapper, MealIngredientDao mealIngredientDao) {
        this.mealIngredientMapper = mealIngredientMapper;
        this.mealIngredientDao = mealIngredientDao;
    }

    @Override
    public MealDTO toDTO(Meal Meal) {
        return MealDTO.builder()
                .id(Meal.getId())
                .name(Meal.getName())
                .typeOfMeal(Meal.getTypeOfMeal())
                .typeOfPreparing(Meal.getTypeOfPreparing())
                .recipe(Meal.getRecipe())
                .minutesToPrepare(Meal.getMinutesToPrepare())
                .ingredients(mealIngredientMapper.toDTOs(Meal.getMealIngredients()))
                .build();
    }

    @Override
    public Meal toEntity(MealDTO mealDto) {
        return Meal.builder()
                .id(mealDto.getId())
                .name(mealDto.getName().toLowerCase())
                .typeOfMeal(mealDto.getTypeOfMeal())
                .typeOfPreparing(mealDto.getTypeOfPreparing())
                .recipe(mealDto.getRecipe())
                .minutesToPrepare(mealDto.getMinutesToPrepare())
                .mealIngredients(mealIngredientMapper.toEntities(mealDto.getIngredients(), mealDto.getId()))
                .build();
    }

    public List<MealDTO> toDTOs(List<Meal> meals) {
        return meals.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<Meal> toEntities(List<MealDTO> mealsDto) {
        return mealsDto.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

}

