package pl.fit_amam.api.mappers;

import pl.fit_amam.api.dto.MenuDTO;
import pl.fit_amam.api.persistence.entities.Menu;
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
                .userName(menu.getUserName())
                .creationDate(menu.getCreationDate())
                .updateDate(menu.getUpdateDate())
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
                .userName(menu.getUserName())
                .creationDate(menu.getCreationDate())
                .updateDate(menu.getUpdateDate())
                .build();
    }
}
