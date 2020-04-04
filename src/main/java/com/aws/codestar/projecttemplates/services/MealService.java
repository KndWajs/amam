package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.dto.MealIngredientDTO;
import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.exceptions.EmptyRequiredFieldException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.MealMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.repositories.MealDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public MealDTO create(MealDTO meal) throws ObjectIsNullException {
        validateMealObject(meal);
        MealDTO savedMeal = mealMapper.toDTO(mealDao.getRepository().save(mealMapper.toEntity(meal)));

        if(!(meal.getIngredients() == null || meal.getIngredients().isEmpty())) {
            for (MealIngredientDTO mealIngredient : meal.getIngredients()) {
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

    public MealDTO update(MealDTO meal) throws ObjectIsNullException {
        validateMealObject(meal);
        validateMealId(meal.getId());

        MealDTO savedMeal = mealMapper.toDTO(mealDao.getRepository().save(mealMapper.toEntity(meal)));
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
