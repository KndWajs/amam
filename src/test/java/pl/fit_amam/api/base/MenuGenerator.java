package pl.fit_amam.api.base;

import pl.fit_amam.api.dto.MenuDTO;
import pl.fit_amam.api.persistence.entities.Menu;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MenuGenerator {

    public static Menu getSampleMenuEntity() {
        return Menu.builder()
                .numberOfPeople(7D)
                .name("test menu name")
                .archival(false)
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static MenuDTO getSampleMenuDTO() {
        return MenuDTO.builder()
                .numberOfPeople(7)
                .name("test menu name")
                .archival(false)
                .userName("testUserName")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
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
