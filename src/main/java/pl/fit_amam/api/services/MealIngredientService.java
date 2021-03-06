package pl.fit_amam.api.services;


import pl.fit_amam.api.dto.MealIngredientDTO;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.MealIngredientMapper;
import pl.fit_amam.api.persistence.repositories.MealDao;
import pl.fit_amam.api.persistence.repositories.MealIngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MealIngredientService {
    private MealIngredientDao mealIngredientDao;
    private MealIngredientMapper mealIngredientMapper;
    private MealDao mealDao;

    @Autowired
    public MealIngredientService(
            MealIngredientDao mealIngredientDao,
            MealIngredientMapper mealIngredientMapper,
            MealDao mealDao) {
        this.mealIngredientDao = mealIngredientDao;
        this.mealIngredientMapper = mealIngredientMapper;
        this.mealDao = mealDao;
    }

    public MealIngredientDTO create(MealIngredientDTO mealIngredient, Long mealId) throws ObjectIsNullException {
        validateMealIngredientObject(mealIngredient);
        validateMealId(mealId);
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

    private void validateMealId(Long mealId) {
        if (mealId == null || !mealDao.getRepository().existsById(mealId)) {
            throw new ObjectIdDoesNotExistsException(mealId, "meal");
        }
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
