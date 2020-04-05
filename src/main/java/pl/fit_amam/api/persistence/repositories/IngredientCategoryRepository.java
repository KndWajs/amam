package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.IngredientCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientCategoryRepository extends CrudRepository<IngredientCategory, Long> {

}

