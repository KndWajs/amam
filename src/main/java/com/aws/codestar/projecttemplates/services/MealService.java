package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.dto.MealIngredientDTO;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.MealMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MealService {
    private MealRepository mealRepository;
    private MealMapper mealMapper;
    private MealIngredientService mealIngredientService;

    @Autowired
    public MealService(MealRepository mealRepository, MealMapper mealMapper, MealIngredientService mealIngredientService) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
        this.mealIngredientService = mealIngredientService;
    }

    public MealDTO create(MealDTO meal) throws ObjectIsNullException {
        validateMealObject(meal);
        MealDTO savedMeal = mealMapper.toDTO(mealRepository.save(mealMapper.toEntity(meal)));

        for (MealIngredientDTO mealIngredient : meal.getIngredients()) {
            savedMeal.getIngredients().add(mealIngredientService.create(mealIngredient, savedMeal.getId()));
        }

        return savedMeal;
    }

    @Transactional(readOnly = true)
    public MealDTO get(Long id) {
        validateMealId(id);
        return mealMapper.toDTO(mealRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<MealDTO> getAll() {
        List<MealDTO> meals = new ArrayList<>();
        for (Meal meal : mealRepository.findAll()) {
            meals.add(mealMapper.toDTO(meal));
        }
        return meals;
    }

//    public MealDTO update(MealDTO meal) throws ObjectIsNullException {
//        validateMealObject(meal);
//        return mealMapper.toDTO(mealRepository.saveAndFlush(mealMapper.toEntity(meal)));
//    }

    public void delete(Long id) {
        validateMealId(id);
        mealRepository.deleteById(id);
    }

    private void validateMealId(Long mealId) {
        if (mealId == null || !mealRepository.existsById(mealId)) {
            throw new ObjectIdDoesNotExistsException(mealId);
        }
    }

    private void validateMealObject(MealDTO meal) throws ObjectIsNullException {
        if (meal == null) {
            throw new ObjectIsNullException(MealDTO.class.getName());
        }
    }
}
