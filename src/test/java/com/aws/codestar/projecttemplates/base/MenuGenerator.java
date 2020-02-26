package com.aws.codestar.projecttemplates.base;

import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuGenerator {

    public static Menu getSampleMenuEntity() {
        return Menu.builder()
                .numberOfPeople(7D)
                .name("test menu name")
                .archival(false)
                .build();
    }

    public static MenuDTO getSampleMenuDTO() {
        return MenuDTO.builder()
                .numberOfPeople(7)
                .name("test menu name")
                .archival(false)
                .build();
    }

    public static List<Menu> getSampleMenuEntities() {
        List menus = new ArrayList();
        menus.add(getSampleMenuEntity());
        return menus;
    }

    public static List<MenuDTO> getSampleMenuDTOs() {
        List menus = new ArrayList();
        menus.add(getSampleMenuDTO());
        return menus;
    }
}
