package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.dto.IngredientCategoryDTO;
import com.aws.codestar.projecttemplates.mappers.IngredientCategoryMapper;
import com.aws.codestar.projecttemplates.persistence.entities.IngredientCategory;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class IngredientCategoryService {
    private IngredientCategoryRepository ingredientCategoryRepository;
    private IngredientCategoryMapper ingredientCategoryMapper;

    @Autowired
    public IngredientCategoryService(IngredientCategoryRepository ingredientCategoryRepository,
                                     IngredientCategoryMapper ingredientCategoryMapper) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
        this.ingredientCategoryMapper = ingredientCategoryMapper;
    }

    public List<IngredientCategoryDTO> getAllIngredientCategories() {
        return StreamSupport
                .stream(ingredientCategoryRepository.findAll().spliterator(), false)
                .map(category -> ingredientCategoryMapper.toDTO(category))
                .collect(Collectors.toList());
    }

    public IngredientCategoryDTO addNewIngredient(IngredientCategoryDTO ingredientCategoryDTO) {
        IngredientCategory newIngredientCategory = new IngredientCategory();
        newIngredientCategory.setCategory(ingredientCategoryDTO.getCategory().toLowerCase());
        return ingredientCategoryMapper.toDTO(ingredientCategoryRepository.save(newIngredientCategory));
    }
}
