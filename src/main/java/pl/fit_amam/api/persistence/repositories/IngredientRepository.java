package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}

