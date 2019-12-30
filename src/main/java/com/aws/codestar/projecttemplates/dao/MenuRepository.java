package com.aws.codestar.projecttemplates.dao;

import com.aws.codestar.projecttemplates.entities.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

}

