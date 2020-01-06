package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.MealDTO;
import com.aws.codestar.projecttemplates.dto.MenuDTO;
import com.aws.codestar.projecttemplates.dto.MenuMealDTO;
import com.aws.codestar.projecttemplates.dto.MenuParametersDTO;
import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.MenuMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.persistence.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuService {
    private MenuRepository menuRepository;
    private MenuMapper menuMapper;
    private MenuMealService menuMealService;
    private MealService mealService;

    @Autowired
    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper, MenuMealService menuMealService, MealService mealService) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.menuMealService = menuMealService;
        this.mealService = mealService;
    }

    public MenuDTO create(MenuDTO menu) throws ObjectIsNullException {
        validateMenuObject(menu);
        MenuDTO savedMenu = menuMapper.toDTO(menuRepository.save(menuMapper.toEntity(menu)));

        for (MenuMealDTO menuMeal : menu.getMeals()) {
            savedMenu.getMeals().add(menuMealService.create(menuMeal, savedMenu.getId()));
        }

        return savedMenu;
    }

    @Transactional(readOnly = true)
    public MenuDTO get(Long id) {
        validateMenuId(id);
        return menuMapper.toDTO(menuRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<MenuDTO> getAll() {
        List<MenuDTO> menus = new ArrayList<>();
        for (Menu menu : menuRepository.findAll()) {
            menus.add(menuMapper.toDTO(menu));
        }
        return menus;
    }

//    public MenuDTO update(MenuDTO menu) throws ObjectIsNullException {
//        validateMenuObject(menu);
//        return menuMapper.toDTO(menuRepository.saveAndFlush(menuMapper.toEntity(menu)));
//    }

    public void delete(Long id) {
        validateMenuId(id);
        menuRepository.deleteById(id);
    }

    private void validateMenuId(Long menuId) {
        if (menuId == null || !menuRepository.existsById(menuId)) {
            throw new ObjectIdDoesNotExistsException(menuId);
        }
    }

    private void validateMenuObject(MenuDTO menu) throws ObjectIsNullException {
        if (menu == null) {
            throw new ObjectIsNullException(MenuDTO.class.getName());
        }
    }

    public MenuDTO createMenuProposal(MenuParametersDTO menuParametersDTO) {
        return MenuDTO.builder()
                .id(null)
                .numberOfPeople(menuParametersDTO.getNumberOfPersons())
                .name(menuParametersDTO.getName())
                .meals(findRandomMenus(menuParametersDTO.getNumberOfDays(), menuParametersDTO.getMealTypes()))
                .build();
    }

    private List<MenuMealDTO> findRandomMenus(int numberOfDays, List<MealType> mealTypes) {

        List<MenuMealDTO> menuMealDTOS = new ArrayList<>();

        for (int i = 1; i <= numberOfDays; i++) {
            for (MealType mealType:mealTypes){
                List<MealDTO> meals = mealService.getMealsByType(mealType);

                menuMealDTOS.add(
                MenuMealDTO.builder()
                        .meal(meals.get((int) (Math.random() * (meals.size()))))
                        .dayNumber(i)
                        .build());
            }
        }
        return menuMealDTOS;
    }
}
