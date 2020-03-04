package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper implements Mapper<MenuDTO, Menu> {
    private MenuMealMapper menuMealMapper;

    @Autowired
    public MenuMapper(MenuMealMapper menuMealMapper) {
        this.menuMealMapper = menuMealMapper;
    }


    @Override
    public MenuDTO toDTO(Menu menu) {
        if(menu == null){
            return null;
        }
        return MenuDTO.builder()
                .id(menu.getId())
                .numberOfPeople(menu.getNumberOfPeople())
                .name(menu.getName())
                .meals(menuMealMapper.toDTOs(menu.getMenuMeals()))
                .archival(menu.isArchival())
                .build();
    }

    @Override
    public Menu toEntity(MenuDTO menu) {
        if(menu == null){
            return null;
        }

        return Menu.builder()
                .id(menu.getId())
                .numberOfPeople(menu.getNumberOfPeople())
                .name(menu.getName() == null ? null : menu.getName().toLowerCase())
                .menuMeals(menuMealMapper.toEntities(menu.getMeals(), menu.getId()))
                .archival(menu.isArchival())
                .build();
    }
}
