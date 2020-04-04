package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.IngredientMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientDao;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IngredientService {
    private IngredientRepository ingredientRepository;
    private IngredientDao ingredientDao;
    private IngredientMapper ingredientMapper;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, IngredientDao ingredientDao,
                             IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientDao = ingredientDao;
        this.ingredientMapper = ingredientMapper;
    }

    public IngredientDTO create(IngredientDTO ingredientDTO) throws ObjectIsNullException {
        validateIngredientObject(ingredientDTO);
        Ingredient outcomeIngredient =
                ingredientDao.getIngredientByNameAndUnit(ingredientMapper.toEntity(ingredientDTO));
        if (outcomeIngredient == null) {
            return ingredientMapper.toDTO(ingredientRepository.save(ingredientMapper.toEntity(ingredientDTO)));
        }
        //TODO give response that meal exists
        return ingredientMapper.toDTO(outcomeIngredient);
    }

    @Transactional(readOnly = true)
    public IngredientDTO get(Long id) {
        validateIngredientId(id);
        return ingredientMapper.toDTO(ingredientRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<IngredientDTO> getAll() {
        List<IngredientDTO> ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientRepository.findAll()) {
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
//        return ingredientMapper.toDTO(ingredientRepository.saveAndFlush(ingredientMapper.toEntity(ingredient)));
//    }


    public IngredientDTO update(IngredientDTO ingredientDTO, Long id) {
        validateIngredientId(id);
        validateIngredientObject(ingredientDTO);
        return ingredientMapper.toDTO(ingredientRepository.save(ingredientMapper.toEntity(ingredientDTO)));
    }

    public void delete(Long id) {
        validateIngredientId(id);
        ingredientRepository.deleteById(id);
    }

    private void validateIngredientId(Long ingredientId) {
        if (ingredientId == null || !ingredientRepository.existsById(ingredientId)) {
            throw new ObjectIdDoesNotExistsException(ingredientId);
        }
    }

    private void validateIngredientObject(IngredientDTO ingredient) throws ObjectIsNullException {
        if (ingredient == null) {
            throw new ObjectIsNullException(IngredientDTO.class.getName());
        }
    }
}
