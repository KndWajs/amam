package com.aws.codestar.projecttemplates.controller;


import com.aws.codestar.projecttemplates.dao.MenuRepository;
import com.aws.codestar.projecttemplates.dto.MenuDto;
import com.aws.codestar.projecttemplates.entities.Menu;
import com.aws.codestar.projecttemplates.mappers.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class MenuController {

    private MenuRepository menuRepository;
    private MenuMapper menuMapper;

    @Autowired
    public MenuController(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @GetMapping(path = "/menus/")
    public @ResponseBody
    List<MenuDto> getAllMenus() {
        List<MenuDto> menus = new ArrayList<>();
        for (Menu menu : menuRepository.findAll()) {
            menus.add(menuMapper.toDto(menu));
        }
        return menus;
    }

    @GetMapping(path = "/menu")
    public @ResponseBody
    MenuDto getMenuByName(@PathParam("name") String name) {
        return null;
    }

//    @GetMapping(path = "/menu")
//    public @ResponseBody
//    MenuDto getMenusById(@PathParam("id") Long id) {
//        return menuMapper.toDto(menuRepository.findById(id.intValue()).get()); //TODO redirect to service
//    }

    @PostMapping(path = "/menu")
    public @ResponseBody
    MenuDto addNewMenu(@RequestBody MenuDto menudto) {
        return null;
    }

    @PutMapping(path = "/menu/{id}")
    public @ResponseBody
    MenuDto updateMenu(@RequestBody MenuDto menudto, @PathVariable Long id) {
        return null;
    }


}
