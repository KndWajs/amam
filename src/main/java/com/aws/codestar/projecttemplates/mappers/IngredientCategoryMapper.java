package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.IngredientCategoryDTO;
import com.aws.codestar.projecttemplates.persistence.entities.IngredientCategory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IngredientCategoryMapper implements Mapper<IngredientCategoryDTO, IngredientCategory> {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public IngredientCategoryDTO toDTO(IngredientCategory ingredientCategory) {
        if (ingredientCategory == null) {
            return null;
        }

        IngredientCategoryDTO ingredientCategoryDTO = modelMapper.map(ingredientCategory, IngredientCategoryDTO.class);
        return ingredientCategoryDTO;
    }

    @Override
    public IngredientCategory toEntity(IngredientCategoryDTO ingredientCategoryDTO) {
        if (ingredientCategoryDTO == null) {
            return null;
        }

        IngredientCategory ingredientCategory = modelMapper.map(ingredientCategoryDTO, IngredientCategory.class);
        return ingredientCategory;
    }
}
