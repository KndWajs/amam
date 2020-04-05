package pl.fit_amam.api.controllers;


import pl.fit_amam.api.Globals;
import pl.fit_amam.api.dto.MenuDTO;
import pl.fit_amam.api.dto.ShoppingListDTO;
import pl.fit_amam.api.services.ShoppingListService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
@Tag(name = "ShoppingListController", description = "desc class")
public class ShoppingListController {

    private ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(path = "/shopping-list")
    public @ResponseBody
    ShoppingListDTO addNewShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {
        return shoppingListService.create(shoppingListDTO);
    }

    @PostMapping(path = "/shopping-list/create-from-menu")
    public @ResponseBody
    ShoppingListDTO createShoppingList(@RequestBody MenuDTO menu) {
        return shoppingListService.create(menu);
    }

    @GetMapping(path = "/shopping-list")
    public @ResponseBody
    List<ShoppingListDTO> getMenus(@PathParam("archival") boolean archival) {
        return shoppingListService.getAll(archival);
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
        return deletedShoppingList;
    }
}
