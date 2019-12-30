package com.aws.codestar.projecttemplates.dao;

import com.aws.codestar.projecttemplates.entities.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

}

