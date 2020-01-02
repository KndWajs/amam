package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.persistence.repositories.MenuRepository;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingListRepository;
import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import com.aws.codestar.projecttemplates.mappers.MenuMapper;
import com.aws.codestar.projecttemplates.mappers.ShoppingListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class MenuController {

    private MenuRepository menuRepository;
    private MenuMapper menuMapper;

    private ShoppingListRepository shoppingListRepository;
    private ShoppingListMapper shoppingListMapper;

    @Autowired
    public MenuController(MenuRepository menuRepository, MenuMapper menuMapper, ShoppingListRepository shoppingListRepository, ShoppingListMapper shoppingListMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListMapper = shoppingListMapper;
    }

//    @GetMapping(path = "/menus/") TODO
//    public @ResponseBody
//    List<MenuDto> getAllMenus() {
//        List<MenuDto> menus = new ArrayList<>();
//        for (Menu menu : menuRepository.findAll()) {
//            menus.add(menuMapper.toDto(menu));
//        }
//        return menus;
//    }

    @PostMapping(path = "/menu")
    public @ResponseBody
    MenuDTO addNewMenu(@RequestBody MenuDTO menuDto) {
        return null; //TODO implement
    }

    @PutMapping(path = "/menu/{id}")
    public @ResponseBody
    MenuDTO updateMenu(@RequestBody MenuDTO menuDto, @PathVariable Long id) {
        return null; //TODO implement
    }

    @DeleteMapping(path = "/menu/{id}")
    public @ResponseBody
    MenuDTO deleteMenu(@PathVariable Long id) {
        return null; //TODO implement
    }

    @GetMapping(path = "/menu/{id}/shopping-list")
    public @ResponseBody
    List<ShoppingListDTO> getShoppingListForMenu() {
        List<ShoppingListDTO> shoppingLists = new ArrayList<>();
        for (ShoppingList sl : shoppingListRepository.findAll()) {
            shoppingLists.add(shoppingListMapper.toDTO(sl));
        }
        return shoppingLists; //TODO add mapers
        //todo find by id
    }

}
