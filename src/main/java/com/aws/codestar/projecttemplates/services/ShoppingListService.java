package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.ShoppingListProposalElementDTO;
import com.aws.codestar.projecttemplates.mappers.ShoppingListProposalElementMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingListProposalElement;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingListProposalElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShoppingListService {
    private ShoppingListProposalElementRepository shoppingListProposalElementRepository;
    private ShoppingListProposalElementMapper shoppingListProposalElementMapper;

    @Autowired
    public ShoppingListService(ShoppingListProposalElementRepository shoppingListProposalElementRepository,
                               ShoppingListProposalElementMapper shoppingListProposalElementMapper) {
        this.shoppingListProposalElementRepository = shoppingListProposalElementRepository;
        this.shoppingListProposalElementMapper = shoppingListProposalElementMapper;
    }

    public List<List<ShoppingListProposalElementDTO>> getAllShoppingListsProposals() {
        List<ShoppingListProposalElement> shoppingListProposalElements = new ArrayList<>();
        for (ShoppingListProposalElement sl : shoppingListProposalElementRepository.findAll()) {
            shoppingListProposalElements.add(sl);
        }

        List<Menu> menus = shoppingListProposalElements.stream().map(item -> item.getMenu()).distinct().collect(Collectors.toList());

        List<List<ShoppingListProposalElementDTO>> shoppingLists = new ArrayList();

        for (Menu menu : menus) {
            shoppingLists.add(shoppingListProposalElements
                    .stream()
                    .filter(item -> item.getMenu() == menu)
                    .map(item -> shoppingListProposalElementMapper.toDTO(item))
                    .collect(Collectors.toList()));
        }

        return shoppingLists;
    }
}
