package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.MenuDto;
import com.aws.codestar.projecttemplates.entities.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper implements Mapper<MenuDto, Menu>{

    @Override
    public MenuDto toDto(Menu entity) {
        return null;
    }

    @Override
    public Menu toEntity(MenuDto menuDto) {
        return null;
    }
}
