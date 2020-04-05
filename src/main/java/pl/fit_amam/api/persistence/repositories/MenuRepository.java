package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

}

