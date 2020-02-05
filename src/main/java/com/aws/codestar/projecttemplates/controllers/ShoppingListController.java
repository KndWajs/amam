package com.aws.codestar.projecttemplates.controllers;


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

    @GetMapping(path = "/shopping-lists")
    public @ResponseBody
    List<List<ShoppingListProposalElementDTO>> getShoppingListForAllMenus() {

        return shoppingListService.getAllShoppingListsProposals();
        //todo find by user id
    }
}
