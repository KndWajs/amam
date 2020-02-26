package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.Globals;
import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.dto.MenuParametersDTO;
import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.mappers.ShoppingListProposalElementMapper;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingElementRepository;
import com.aws.codestar.projecttemplates.services.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
@Tag(name = "MenuController", description = "desc class")
public class MenuController {

    private MenuService menuService;

    private ShoppingElementRepository shoppingElementRepository;
    private ShoppingListProposalElementMapper shoppingListProposalElementMapper;

    @Autowired
    public MenuController(MenuService menuService, ShoppingElementRepository shoppingElementRepository, ShoppingListProposalElementMapper shoppingListProposalElementMapper) {
        this.menuService = menuService;
        this.shoppingElementRepository = shoppingElementRepository;
        this.shoppingListProposalElementMapper = shoppingListProposalElementMapper;
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
    MenuDTO updateMenu(@RequestBody MenuDTO menudDTO) {
        return menuService.update(menudDTO);
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
