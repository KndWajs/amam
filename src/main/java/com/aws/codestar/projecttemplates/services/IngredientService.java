package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.exceptions.EmptyRequiredFieldException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.IngredientMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IngredientService {
    private IngredientDao ingredientDao;
    private IngredientMapper ingredientMapper;

    @Autowired
    public IngredientService( IngredientDao ingredientDao, IngredientMapper ingredientMapper) {
        this.ingredientDao = ingredientDao;
        this.ingredientMapper = ingredientMapper;
    }

    public IngredientDTO create(IngredientDTO ingredientDTO) throws ObjectIsNullException {
        validateIngredientObject(ingredientDTO);
        Ingredient outcomeIngredient =
                ingredientDao.getIngredientByNameAndUnit(ingredientMapper.toEntity(ingredientDTO));
        if (outcomeIngredient == null) {
            return ingredientMapper.toDTO(ingredientDao.getRepository().save(ingredientMapper.toEntity(ingredientDTO)));
        }
        //TODO give response that meal exists
        return ingredientMapper.toDTO(outcomeIngredient);
    }

    @Transactional(readOnly = true)
    public IngredientDTO get(Long id) {
        validateIngredientId(id);
        return ingredientMapper.toDTO(ingredientDao.getRepository().findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<IngredientDTO> getAll() {
        List<IngredientDTO> ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientDao.getRepository().findAll()) {
            ingredients.add(ingredientMapper.toDTO(ingredient));
        }
        return ingredients;
    }

    public List<IngredientDTO> getIngredientsByPartialName(String name, int numberOfResults) {
        List<IngredientDTO> ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientDao.getIngredientsByPartialName(name.toLowerCase(), numberOfResults)) {
            ingredients.add(ingredientMapper.toDTO(ingredient));
        }
        return ingredients;
    }

//    public IngredientDTO update(IngredientDTO ingredient) throws ObjectIsNullException {
//        validateIngredientObject(ingredient);
//        return ingredientMapper.toDTO(ingredientDao.getRepository().saveAndFlush(ingredientMapper.toEntity(ingredient)));
//    }


    public IngredientDTO update(IngredientDTO ingredientDTO, Long id) {
        validateIngredientId(id);
        validateIngredientObject(ingredientDTO);
        return ingredientMapper.toDTO(ingredientDao.getRepository().save(ingredientMapper.toEntity(ingredientDTO)));
    }

    public void delete(Long id) {
        validateIngredientId(id);
        ingredientDao.getRepository().deleteById(id);
    }

    private void validateIngredientId(Long ingredientId) {
        if (ingredientId == null || !ingredientDao.getRepository().existsById(ingredientId)) {
            throw new ObjectIdDoesNotExistsException(ingredientId);
        }
    }

    private void validateIngredientObject(IngredientDTO ingredient) throws ObjectIsNullException {
        if (ingredient == null) {
            throw new ObjectIsNullException(IngredientDTO.class.getName());
        }
        if (StringUtils.isEmpty(ingredient.getName())) {
            throw new EmptyRequiredFieldException("Name");
        }
        if (ingredient.getIngredientUnit() == null) {
            throw new EmptyRequiredFieldException("Ingredient Unit");
        }
    }
}
