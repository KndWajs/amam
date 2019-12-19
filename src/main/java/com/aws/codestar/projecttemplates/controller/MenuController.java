package com.aws.codestar.projecttemplates.controller;


import com.aws.codestar.projecttemplates.dao.MenuRepository;
import com.aws.codestar.projecttemplates.dao.ShoppingListRepository;
import com.aws.codestar.projecttemplates.dto.MenuDto;
import com.aws.codestar.projecttemplates.dto.ShoppingListDto;
import com.aws.codestar.projecttemplates.entities.ShoppingList;
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
    MenuDto addNewMenu(@RequestBody MenuDto menuDto) {
        return null; //TODO implement
    }

    @PutMapping(path = "/menu/{id}")
    public @ResponseBody
    MenuDto updateMenu(@RequestBody MenuDto menuDto, @PathVariable Long id) {
        return null; //TODO implement
    }

    @DeleteMapping(path = "/menu/{id}")
    public @ResponseBody
    MenuDto deleteMenu(@PathVariable Long id) {
        return null; //TODO implement
    }

    @GetMapping(path = "/menu/{id}/shopping-list")
    public @ResponseBody
    List<ShoppingListDto> getShoppingListForMenu() {
        List<ShoppingListDto> shoppingLists = new ArrayList<>();
        for (ShoppingList sl : shoppingListRepository.findAll()) {
            shoppingLists.add(shoppingListMapper.toDto(sl));
        }
        return shoppingLists; //TODO add mapers
        //todo find by id
    }

}
