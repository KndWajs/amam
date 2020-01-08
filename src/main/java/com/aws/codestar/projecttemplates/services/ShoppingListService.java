package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.ShoppingElementDTO;
import com.aws.codestar.projecttemplates.mappers.ShoppingElementMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingElement;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShoppingListService {
    private ShoppingElementRepository shoppingElementRepository;
    private ShoppingElementMapper shoppingElementMapper;

    @Autowired
    public ShoppingListService(ShoppingElementRepository shoppingElementRepository, ShoppingElementMapper shoppingElementMapper) {
        this.shoppingElementRepository = shoppingElementRepository;
        this.shoppingElementMapper = shoppingElementMapper;
    }

    public List<List<ShoppingElementDTO>> getAllShoppingLists() {
        List<ShoppingElement> shoppingElements = new ArrayList<>();
        for (ShoppingElement sl : shoppingElementRepository.findAll()) {
            shoppingElements.add(sl);
        }

        List<Menu> menus = shoppingElements.stream().map(item -> item.getMenu()).distinct().collect(Collectors.toList());

        List<List<ShoppingElementDTO>> shoppingLists = new ArrayList();

        for (Menu menu: menus) {
            shoppingLists.add(shoppingElements
                    .stream()
                    .filter(item -> item.getMenu() == menu)
                    .map(item -> shoppingElementMapper.toDTO(item))
                    .collect(Collectors.toList()));
        }

        return shoppingLists;
    }
}
