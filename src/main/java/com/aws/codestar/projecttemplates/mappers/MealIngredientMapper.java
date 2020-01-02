package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.persistence.repositories.MealRepository;
import com.aws.codestar.projecttemplates.dto.MealIngredientDTO;
import com.aws.codestar.projecttemplates.persistence.entities.MealIngredient;
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

    public MealIngredientDTO toDTO(MealIngredient mealIngredient) {
        return MealIngredientDTO.builder()
                .ingredient(ingredientMapper.toDTO(mealIngredient.getIngredient()))
                .amount(mealIngredient.getAmount())
                .build();
    }

    public MealIngredient toEntity(MealIngredientDTO mealIngredientDto, Long mealId) {
        return MealIngredient.builder()
                .meal(mealId == null ? null : mealRepository.findById(mealId).get())
                .ingredient(ingredientMapper.toEntity(mealIngredientDto.getIngredient()))
                .amount(mealIngredientDto.getAmount())
                .build();
    }

    public List<MealIngredientDTO> toDTOs(List<MealIngredient> mealIngredients) {
        return mealIngredients.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<MealIngredient> toEntities(List<MealIngredientDTO> mealIngredientsDto, Long mealId) {
        return mealIngredientsDto.stream().map(dto -> toEntity(dto, mealId)).collect(Collectors.toList());
    }
}
