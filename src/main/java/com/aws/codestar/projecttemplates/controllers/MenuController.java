package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.dto.MenuParametersDTO;
import com.aws.codestar.projecttemplates.mappers.ShoppingElementMapper;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingElementRepository;
import com.aws.codestar.projecttemplates.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins = "*")
public class MenuController {

    private MenuService menuService;

    private ShoppingElementRepository shoppingElementRepository;
    private ShoppingElementMapper shoppingElementMapper;

    @Autowired
    public MenuController(MenuService menuService, ShoppingElementRepository shoppingElementRepository, ShoppingElementMapper shoppingElementMapper) {
        this.menuService = menuService;
        this.shoppingElementRepository = shoppingElementRepository;
        this.shoppingElementMapper = shoppingElementMapper;
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

    @PostMapping(path = "/menu/parameters")
    public @ResponseBody
    MenuDTO createMenu(@RequestBody MenuParametersDTO menuParametersDTO) {
        return menuService.createMenuProposal(menuParametersDTO);
    }

}
