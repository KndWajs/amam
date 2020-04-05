package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.MealIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealIngredientRepository extends CrudRepository<MealIngredient, Long> {

}

