package pl.fit_amam.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.fit_amam.api.Globals;
import pl.fit_amam.api.dto.MenuDTO;
import pl.fit_amam.api.dto.MenuParametersDTO;
import pl.fit_amam.api.services.MenuService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/" + Globals.API_VERSION + "/")
@CrossOrigin(origins = "*")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping(path = "/menu")
    public @ResponseBody
    MenuDTO addNewMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.create(menuDTO);
    }

    @PostMapping(path = "/menu/parameters")
    public @ResponseBody
    MenuDTO createMenu(@RequestBody MenuParametersDTO menuParametersDTO) {
        return menuService.createMenuProposal(menuParametersDTO);
    }

    @GetMapping(path = "/menus")
    public @ResponseBody
    List<MenuDTO> getMenus(@PathParam("archival") boolean archival) {
        return menuService.getAll(archival);
    }

    @GetMapping(path = "/menu")
    public @ResponseBody
    MenuDTO getMenuById(@PathParam("id") Long id) {
        return menuService.get(id);
    }

    @PutMapping(path = "/menu")
    public @ResponseBody
    MenuDTO updateMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.update(menuDTO);
    }

    @DeleteMapping(path = "/menu/{id}")
    public @ResponseBody
    MenuDTO deleteMenu(@PathVariable Long id) {
        MenuDTO deletedMenu = menuService.get(id);
        menuService.delete(id);
        return deletedMenu;
    }
}
