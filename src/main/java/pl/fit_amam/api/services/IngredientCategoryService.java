package pl.fit_amam.api.services;

import pl.fit_amam.api.dto.IngredientCategoryDTO;
import pl.fit_amam.api.dto.IngredientDTO;
import pl.fit_amam.api.exceptions.EmptyRequiredFieldException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.IngredientCategoryMapper;
import pl.fit_amam.api.persistence.entities.IngredientCategory;
import pl.fit_amam.api.persistence.repositories.IngredientCategoryRepository;
import org.apache.commons.lang3.StringUtils;
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

    public IngredientCategoryDTO create(IngredientCategoryDTO ingredientCategoryDTO) {
        validateIngredientCategoryObject(ingredientCategoryDTO);
        IngredientCategory newIngredientCategory = new IngredientCategory();
        newIngredientCategory.setCategory(ingredientCategoryDTO.getCategory().toLowerCase());
        return ingredientCategoryMapper.toDTO(ingredientCategoryRepository.save(newIngredientCategory));
    }

    public List<IngredientCategoryDTO> getAll() {
        return StreamSupport
                .stream(ingredientCategoryRepository.findAll().spliterator(), false)
                .map(category -> ingredientCategoryMapper.toDTO(category))
                .collect(Collectors.toList());
    }

    private void validateIngredientCategoryObject(IngredientCategoryDTO ingredientCategory) {
        if (ingredientCategory == null) {
            throw new ObjectIsNullException(IngredientDTO.class.getName());
        }
        if (StringUtils.isEmpty(ingredientCategory.getCategory())) {
            throw new EmptyRequiredFieldException("Category");
        }
    }
}
