package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.MealIngredientDTO;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.MealIngredientMapper;
import com.aws.codestar.projecttemplates.persistence.repositories.MealIngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MealIngredientService {
    private MealIngredientDao mealIngredientDao;
    private MealIngredientMapper mealIngredientMapper;

    @Autowired
    public MealIngredientService(MealIngredientDao mealIngredientDao,
                                 MealIngredientMapper mealIngredientMapper) {
        this.mealIngredientDao = mealIngredientDao;
        this.mealIngredientMapper = mealIngredientMapper;
    }

    public MealIngredientDTO create(MealIngredientDTO mealIngredient, Long mealId) throws ObjectIsNullException {
        validateMealIngredientObject(mealIngredient);
        return mealIngredientMapper
                .toDTO(mealIngredientDao.getRepository().save(mealIngredientMapper.toEntity(mealIngredient, mealId)));
    }

    @Transactional(readOnly = true)
    public MealIngredientDTO get(Long id) {
        validateMealIngredientId(id);
        return mealIngredientMapper.toDTO(mealIngredientDao.getRepository().findById(id).get());
    }

    public void delete(Long id) {
        validateMealIngredientId(id);
        mealIngredientDao.getRepository().deleteById(id);
    }

    private void validateMealIngredientId(Long mealIngredientId) {
        if (mealIngredientId == null || !mealIngredientDao.getRepository().existsById(mealIngredientId)) {
            throw new ObjectIdDoesNotExistsException(mealIngredientId);
        }
    }

    private void validateMealIngredientObject(MealIngredientDTO mealIngredient) throws ObjectIsNullException {
        if (mealIngredient == null) {
            throw new ObjectIsNullException(MealIngredientDTO.class.getName());
        }
    }
}
