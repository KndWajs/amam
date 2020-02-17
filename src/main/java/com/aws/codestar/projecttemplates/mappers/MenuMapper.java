package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper implements Mapper<MenuDTO, Menu>{
    private MenuMealMapper menuMealMapper;

    @Autowired
    public MenuMapper(MenuMealMapper menuMealMapper) {
        this.menuMealMapper = menuMealMapper;
    }


    @Override
    public MenuDTO toDTO(Menu menu) {
        return MenuDTO.builder()
                .id(menu.getId())
                .numberOfPeople(menu.getNumberOfPeople())
                .name(menu.getName())
                .meals(menuMealMapper.toDTOs(menu.getMenuMeals()))
                .archival(menu.isArchival())
                .build();
    }

    @Override
    public Menu toEntity(MenuDTO menuDTO) {
        return Menu.builder()
                .id(menuDTO.getId())
                .numberOfPeople(menuDTO.getNumberOfPeople())
                .name(menuDTO.getName().toLowerCase())
                .menuMeals(menuMealMapper.toEntities(menuDTO.getMeals(), menuDTO.getId()))
                .archival(menuDTO.isArchival())
                .build();
    }
}
