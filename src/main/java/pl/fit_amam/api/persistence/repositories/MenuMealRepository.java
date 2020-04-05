package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.MenuMeal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuMealRepository extends CrudRepository<MenuMeal, Long> {
}

