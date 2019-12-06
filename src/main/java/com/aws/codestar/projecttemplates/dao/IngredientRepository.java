package com.aws.codestar.projecttemplates.dao;

import com.aws.codestar.projecttemplates.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}

