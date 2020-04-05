package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

}

