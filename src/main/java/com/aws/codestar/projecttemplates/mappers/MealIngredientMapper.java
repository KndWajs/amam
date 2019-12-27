package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dao.MealRepository;
import com.aws.codestar.projecttemplates.dto.MealIngredientDto;
import com.aws.codestar.projecttemplates.entities.MealIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealIngredientMapper {

    private IngredientMapper ingredientMapper;
    private MealRepository mealRepository;

    @Autowired
    public MealIngredientMapper(IngredientMapper ingredientMapper, MealRepository mealRepository) {
        this.ingredientMapper = ingredientMapper;
        this.mealRepository = mealRepository;
    }

    public MealIngredientDto toDto(MealIngredient mealIngredient) {
        return MealIngredientDto.builder()
                .ingredient(ingredientMapper.toDto(mealIngredient.getIngredient()))
                .amount(mealIngredient.getAmount())
                .build();
    }

    public MealIngredient toEntity(MealIngredientDto mealIngredientDto, Long mealId) {
        return MealIngredient.builder()
                .meal(mealRepository.findById(mealId.intValue()).get())
                .ingredient(ingredientMapper.toEntity(mealIngredientDto.getIngredient()))
                .amount(mealIngredientDto.getAmount())
                .build();
    }

    public List<MealIngredientDto> toDtos(List<MealIngredient> mealIngredients) {
        return mealIngredients.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }

    public List<MealIngredient> toEntities(List<MealIngredientDto> mealIngredientsDto, Long mealId) {
        return mealIngredientsDto.stream().map(dto -> toEntity(dto, mealId)).collect(Collectors.toList());
    }
}
