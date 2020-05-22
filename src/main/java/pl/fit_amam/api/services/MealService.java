package pl.fit_amam.api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.fit_amam.api.dto.MealDTO;
import pl.fit_amam.api.dto.MealIngredientDTO;
import pl.fit_amam.api.enums.MealType;
import pl.fit_amam.api.exceptions.EmptyRequiredFieldException;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.MealMapper;
import pl.fit_amam.api.persistence.entities.Meal;
import pl.fit_amam.api.persistence.repositories.MealDao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MealService {
    private MealMapper mealMapper;
    private MealIngredientService mealIngredientService;
    private MealDao mealDao;

    @Autowired
    public MealService(MealMapper mealMapper,
                       MealIngredientService mealIngredientService, MealDao mealDao) {
        this.mealMapper = mealMapper;
        this.mealIngredientService = mealIngredientService;
        this.mealDao = mealDao;
    }

    public MealDTO create(MealDTO mealDTO) throws ObjectIsNullException {
        validateMealObject(mealDTO);

        Meal meal = mealMapper.toEntity(mealDTO);
        meal.setCreationDate(new Timestamp(System.currentTimeMillis()));
        meal.setUserName(UserService.getUserName());

        MealDTO savedMeal = mealMapper.toDTO(mealDao.getRepository().save(meal));

        if(!(mealDTO.getIngredients() == null || mealDTO.getIngredients().isEmpty())) {
            for (MealIngredientDTO mealIngredient : mealDTO.getIngredients()) {
                savedMeal.getIngredients().add(mealIngredientService.create(mealIngredient, savedMeal.getId()));
            }
        }

        return savedMeal;
    }

    @Transactional(readOnly = true)
    public MealDTO get(Long id) {
        validateMealId(id);
        return mealMapper.toDTO(mealDao.getRepository().findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<MealDTO> getAll() {
        List<MealDTO> meals = new ArrayList<>();
        for (Meal meal : mealDao.getRepository().findAll()) {
            meals.add(mealMapper.toDTO(meal));
        }
        return meals;
    }

    @Transactional(readOnly = true)
    public List<MealDTO> getMealsByType(MealType mealType) {
        List<MealDTO> meals = new ArrayList<>();
        for (Meal meal : mealDao.getMealsByType(mealType)) {
            meals.add(mealMapper.toDTO(meal));
        }
        return meals;
    }

    public List<MealDTO> getMealsByTypeWithoutCertainMeals(MealType mealType, List<MealDTO> excludedMeals) {
        List<MealDTO> meals = new ArrayList<>();
        for (Meal meal : mealDao.getMealsByTypeWithoutCertainMeals(mealType, mealMapper.toEntities(excludedMeals))) {
            meals.add(mealMapper.toDTO(meal));
        }
        return meals;
    }

    @Transactional(readOnly = true)
    public List<MealDTO> getMealsByPartialName(String name, int numberOfResults) {
        List<MealDTO> meals = new ArrayList<>();
        for (Meal meal : mealDao.getMealsByPartialName(name.toLowerCase(), numberOfResults)) {
            meals.add(mealMapper.toDTO(meal));
        }
        return meals;
    }

    public MealDTO update(MealDTO mealDTO) throws ObjectIsNullException {
        validateMealObject(mealDTO);
        validateMealId(mealDTO.getId());

        Meal meal = mealMapper.toEntity(mealDTO);
        meal.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        MealDTO savedMeal = mealMapper.toDTO(mealDao.getRepository().save(meal));
        return savedMeal;
    }

    public void delete(Long id) {
        validateMealId(id);
        mealDao.getRepository().deleteById(id);
    }

    private void validateMealId(Long mealId) {
        if (mealId == null || !mealDao.getRepository().existsById(mealId)) {
            throw new ObjectIdDoesNotExistsException(mealId);
        }
    }

    private void validateMealObject(MealDTO meal) throws ObjectIsNullException {
        if (meal == null) {
            throw new ObjectIsNullException(MealDTO.class.getName());
        }

        if (StringUtils.isEmpty(meal.getName())) {
            throw new EmptyRequiredFieldException("Name");
        }
        if (meal.getTypeOfMeal() == null) {
            throw new EmptyRequiredFieldException("Type Of Meal");
        }
        if (meal.getTypeOfPreparing() == null) {
            throw new EmptyRequiredFieldException("Type Of Preparing");
        }
        if (meal.getMinutesToPrepare() == null) {
            throw new EmptyRequiredFieldException("Minutes to prepare");
        }
    }

}
