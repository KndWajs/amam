package pl.fit_amam.api.controllers;


import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fit_amam.api.Globals;
import pl.fit_amam.api.dto.MenuDTO;
import pl.fit_amam.api.dto.ShoppingListDTO;
import pl.fit_amam.api.services.ShoppingListService;

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

    @GetMapping(path = "/shopping-list/pdf/{id}")
    public @ResponseBody
    ResponseEntity getShoppingListInPdf(@PathVariable Long id)  throws DocumentException{
        InputStreamResource resource = new InputStreamResource(shoppingListService.getPdfList(id));


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=shopping_list:_" + id )
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
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
