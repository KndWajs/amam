package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

}

