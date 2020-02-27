package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.*;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.ShoppingListMapper;
import com.aws.codestar.projecttemplates.mappers.ShoppingListProposalElementMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingListProposalElement;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingListProposalElementRepository;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingListRepository;
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
    private ShoppingListRepository shoppingListRepository;
    private ShoppingListProposalElementMapper shoppingListProposalElementMapper;
    private ShoppingListMapper shoppingListMapper;
    private ShoppingElementService shoppingElementService;

    @Autowired
    public ShoppingListService(ShoppingListProposalElementRepository shoppingListProposalElementRepository,
                               ShoppingListRepository shoppingListRepository,
                               ShoppingListProposalElementMapper shoppingListProposalElementMapper,
                               ShoppingListMapper shoppingListMapper, ShoppingElementService shoppingElementService) {
        this.shoppingListProposalElementRepository = shoppingListProposalElementRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListProposalElementMapper = shoppingListProposalElementMapper;
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingElementService = shoppingElementService;
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

    @Transactional(readOnly = true)
    public List<ShoppingListDTO> getShoppingLists() {
        List<ShoppingListDTO> shoppingListDTOS = new ArrayList<>();
        for (ShoppingList shoppingList : shoppingListRepository.findAll()) {
            shoppingListDTOS.add(shoppingListMapper.toDTO(shoppingList));
        }
        return shoppingListDTOS;
    }

    public ShoppingListDTO create(ShoppingListDTO shoppingListDTO) throws ObjectIsNullException {
        validateShoppingListObject(shoppingListDTO);
        List<ShoppingElementDTO> shoppingElements = shoppingListDTO.getShoppingElements();
        shoppingListDTO.setShoppingElements(new ArrayList<>());
        ShoppingListDTO savedShoppingList = shoppingListMapper.toDTO(shoppingListRepository.save(shoppingListMapper.toEntity(shoppingListDTO)));

        for (ShoppingElementDTO shoppingElementDTO : shoppingElements) {
            savedShoppingList.getShoppingElements().add(shoppingElementDTO);
        }

        return update(savedShoppingList);
    }


    public ShoppingListDTO update(ShoppingListDTO shoppingListDTO) {
        validateShoppingListObject(shoppingListDTO);
        validateShoppingListId(shoppingListDTO.getId());

        ShoppingListDTO savedShoppingList = shoppingListMapper.toDTO(shoppingListRepository.save(shoppingListMapper.toEntity(shoppingListDTO)));

        return savedShoppingList;
    }

    @Transactional(readOnly = true)
    public ShoppingListDTO get(Long id) {
        validateShoppingListId(id);
        return shoppingListMapper.toDTO(shoppingListRepository.findById(id).get());
    }

    public void delete(Long id) {
        validateShoppingListId(id);
        shoppingListRepository.deleteById(id);
    }

    private void validateShoppingListId(Long shoppingListId) {
        if (shoppingListId == null || !shoppingListRepository.existsById(shoppingListId)) {
            throw new ObjectIdDoesNotExistsException(shoppingListId);
        }
    }

    private void validateShoppingListObject(ShoppingListDTO shoppingList) throws ObjectIsNullException {
        if (shoppingList == null) {
            throw new ObjectIsNullException(ShoppingListDTO.class.getName());
        }
    }

}
