package com.aws.codestar.projecttemplates.controllers;


import com.aws.codestar.projecttemplates.dto.ShoppingElementDTO;
import com.aws.codestar.projecttemplates.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins = "*")
public class ShoppingListController {

    private ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService){
        this.shoppingListService = shoppingListService;
    }

    @GetMapping(path = "/shopping-lists")
    public @ResponseBody
    List<List<ShoppingElementDTO>> getShoppingListForAllMenus() {

        return shoppingListService.getAllShoppingLists();
        //todo find by user id
    }
}
