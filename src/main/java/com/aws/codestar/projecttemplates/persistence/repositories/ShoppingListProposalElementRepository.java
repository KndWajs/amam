package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.persistence.entities.ShoppingListProposalElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListProposalElementRepository extends CrudRepository<ShoppingListProposalElement, Long> {

}

