package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.IngredientCategoryDTO;
import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.entities.IngredientCategory;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientCategoryRepository;
import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientCategoryMapper implements Mapper<IngredientCategoryDTO, IngredientCategory> {

    private static final ModelMapper modelMapper = new ModelMapper();

//    @Autowired
//    public IngredientCategoryMapper(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    @Override
    public IngredientCategoryDTO toDTO(IngredientCategory ingredientCategory) {
        if (ingredientCategory == null) {
            return null;
        }

        IngredientCategoryDTO ingredientCategoryDTO = modelMapper.map(ingredientCategory, IngredientCategoryDTO.class);
// TODO
//        postDto.setSubmissionDate(post.getSubmissionDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
        return ingredientCategoryDTO;
    }

    @Override
    public IngredientCategory toEntity(IngredientCategoryDTO ingredientCategoryDTO) {
        if (ingredientCategoryDTO == null) {
            return null;
        }

        IngredientCategory ingredientCategory = modelMapper.map(ingredientCategoryDTO, IngredientCategory.class);
//        TODO
//        ingredientCategory.setSubmissionDate(postDto.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));
        return ingredientCategory;
    }
}
