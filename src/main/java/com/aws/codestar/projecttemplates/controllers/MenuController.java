package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.dto.MenuParametersDTO;
import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.mappers.ShoppingListMapper;
import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingListRepository;
import com.aws.codestar.projecttemplates.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins = "*")
public class MenuController {

    private MenuService menuService;

    private ShoppingListRepository shoppingListRepository;
    private ShoppingListMapper shoppingListMapper;

    @Autowired
    public MenuController(MenuService menuService, ShoppingListRepository shoppingListRepository, ShoppingListMapper shoppingListMapper) {
        this.menuService = menuService;
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListMapper = shoppingListMapper;
    }

    @GetMapping(path = "/menus/")
    public @ResponseBody
    List<MenuDTO> getAllMenus() {
        return menuService.getAll();
    }

//    @GetMapping(path = "/menu")
//    public @ResponseBody
//    MenuDto getMenuByName(@PathParam("name") String name) {
//        return null;
//    }

    @GetMapping(path = "/menu")
    public @ResponseBody
    MenuDTO getMenuById(@PathParam("id") Long id) {
        return menuService.get(id);
    }

    @PostMapping(path = "/menu")
    public @ResponseBody
    MenuDTO addNewMenu(@RequestBody MenuDTO menudto) {
        return menuService.create(menudto);
    }

    @PutMapping(path = "/menu/{id}")
    public @ResponseBody
    MenuDTO updateMenu(@RequestBody MenuDTO menudto, @PathVariable Long id) {
        return null; //TODO implement
    }

    @DeleteMapping(path = "/menu/{id}")
    public @ResponseBody
    MenuDTO deleteMenu(@PathVariable Long id) {
        MenuDTO deletedMenu = menuService.get(id);
        menuService.delete(id);
        return deletedMenu; //TODO check if it is deleted?
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

    @PostMapping(path = "/menu/parameters")
    public @ResponseBody
    MenuDTO createMenu(@RequestBody MenuParametersDTO menuParametersDTO) {
        return menuService.createMenuProposal(menuParametersDTO);
    }

}
