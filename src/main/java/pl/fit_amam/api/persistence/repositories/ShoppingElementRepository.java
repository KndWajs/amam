package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.ShoppingElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingElementRepository extends CrudRepository<ShoppingElement, Long> {

}

