package com.aws.codestar.projecttemplates.dao;

import com.aws.codestar.projecttemplates.entities.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

}

