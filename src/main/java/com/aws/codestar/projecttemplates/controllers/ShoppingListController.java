package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.Globals;
import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.dto.ShoppingListProposalElementDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.services.ShoppingListService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
@Tag(name = "ShoppingListController", description = "desc class")
public class ShoppingListController {

    private ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService){
        this.shoppingListService = shoppingListService;
    }

    @GetMapping(path = "/shopping-lists-proposals")
    public @ResponseBody
    List<List<ShoppingListProposalElementDTO>> getAllShoppingListsProposals() {

        return shoppingListService.getAllShoppingListsProposals();
        //TODO find by user id
    }

    @GetMapping(path = "/shopping-list")
    public @ResponseBody
    List<ShoppingListDTO> getShoppingLists() {

        return shoppingListService.getShoppingLists();
        //TODO find by user id
    }

    @PostMapping(path = "/shopping-list", params = "shoppingListDTO")
    public @ResponseBody
    ShoppingListDTO addNewShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {
        return shoppingListService.create(shoppingListDTO);
    }

    @PutMapping(path = "/shopping-list")
    public @ResponseBody
    ShoppingListDTO updateShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {
        return shoppingListService.update(shoppingListDTO);
    }

    @PostMapping(path = "/shopping-list/create-from-menu")
    public @ResponseBody
    ShoppingListDTO createShoppingList(@RequestBody MenuDTO menu)
    {
        return shoppingListService.create(menu);
    }

    @DeleteMapping(path = "/shopping-list/{id}")
    public @ResponseBody
    ShoppingListDTO deleteShoppingList(@PathVariable Long id) {
        ShoppingListDTO deletedShoppingList = shoppingListService.get(id);
        shoppingListService.delete(id);
        return deletedShoppingList; //TODO check if it is deleted?
    }
}
