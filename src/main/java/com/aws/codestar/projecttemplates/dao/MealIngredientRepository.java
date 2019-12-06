package com.aws.codestar.projecttemplates.dao;

import com.aws.codestar.projecttemplates.entities.MealIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealIngredientRepository extends CrudRepository<MealIngredient, Integer> {

}

