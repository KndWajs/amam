package com.aws.codestar.projecttemplates.mappers;

import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper implements Mapper<MenuDTO, Menu>{

    @Override
    public MenuDTO toDto(Menu entity) {
        return null;
    }

    @Override
    public Menu toEntity(MenuDTO menuDto) {
        return null;
    }
}
