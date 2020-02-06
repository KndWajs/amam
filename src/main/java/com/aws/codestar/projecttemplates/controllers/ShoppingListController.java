package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.dto.ShoppingListDTO;
import com.aws.codestar.projecttemplates.dto.ShoppingListProposalElementDTO;
import com.aws.codestar.projecttemplates.services.ShoppingListService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
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
        //todo find by user id
    }

    @GetMapping(path = "/shopping-lists")
    public @ResponseBody
    List<ShoppingListDTO> getShoppingLists() {

        return shoppingListService.getShoppingLists();
        //todo find by user id
    }

    @PostMapping(path = "/shopping-list")
    public @ResponseBody
    ShoppingListDTO addNewShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {
        return shoppingListService.create(shoppingListDTO);
    }

    @PutMapping(path = "/shopping-list")
    public @ResponseBody
    ShoppingListDTO updateShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {
        return shoppingListService.update(shoppingListDTO);
    }

    @DeleteMapping(path = "/shopping-list/{id}")
    public @ResponseBody
    ShoppingListDTO deleteShoppingList(@PathVariable Long id) {
        ShoppingListDTO deletedShoppingList = shoppingListService.get(id);
        shoppingListService.delete(id);
        return deletedShoppingList; //TODO check if it is deleted?
    }

}
