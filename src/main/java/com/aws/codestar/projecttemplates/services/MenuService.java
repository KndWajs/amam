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
import com.aws.codestar.projecttemplates.persistence.repositories.MenuDao;
import com.aws.codestar.projecttemplates.persistence.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class MenuService {
    private MenuRepository menuRepository;
    private MenuMapper menuMapper;
    private MenuMealService menuMealService;
    private MealService mealService;
    private MenuDao menuDao;

    @Autowired
    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper, MenuMealService menuMealService,
                       MealService mealService, MenuDao menuDao) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.menuMealService = menuMealService;
        this.mealService = mealService;
        this.menuDao = menuDao;
    }

    public MenuDTO create(MenuDTO menu) throws ObjectIsNullException {
        validateMenuObject(menu);
        List<MenuMealDTO> menuMeals = menu.getMeals();
        menu.setMeals(new ArrayList<>());

        MenuDTO savedMenu = menuMapper.toDTO(menuRepository.save(menuMapper.toEntity(menu)));

        for (MenuMealDTO menuMeal : menuMeals) {
            savedMenu.getMeals().add(menuMeal);
        }

        return update(savedMenu);
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

    @Transactional(readOnly = true)
    public List<MenuDTO> getAll(boolean archival) {
        List<MenuDTO> menus = new ArrayList<>();
        for (Menu menu : menuDao.getMenusByArchivalStatus(archival)) {
            menus.add(menuMapper.toDTO(menu));
        }
        return menus;
    }

    public MenuDTO update(MenuDTO menu) throws ObjectIsNullException {
        validateMenuObject(menu);
        validateMenuId(menu.getId());

        MenuDTO savedMenu = menuMapper.toDTO(menuRepository.save(menuMapper.toEntity(menu)));
        return savedMenu;
    }

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
                .meals(createRandomMenu(menuParametersDTO.getNumberOfDays(), menuParametersDTO.getMealTypes(), menuParametersDTO.isDinnerForTwoDays()))
                .build();
    }

    private List<MenuMealDTO> createRandomMenu(int numberOfDays, List<MealType> mealTypes, boolean isOneDinnerForTwoDays) {

        List<MenuMealDTO> menuMealDTOS = new ArrayList<>();
        MealDTO lastDinner = new MealDTO();

        for (int i = 1; i <= numberOfDays; i++) {
            for (MealType mealType:mealTypes){
                if(mealType.equals(MealType.DINNER) && isOneDinnerForTwoDays && i%2 == 0){
                    menuMealDTOS.add(
                            MenuMealDTO.builder()
                                    .meal(lastDinner)
                                    .dayNumber(i)
                                    .build());
                } else {
                    List<MealDTO> mealsAlreadyAdded = new LinkedList<>();
                    for (MenuMealDTO menuMealDTO:menuMealDTOS) {
                        mealsAlreadyAdded.add(menuMealDTO.getMeal());
                    }

                    List<MealDTO> meals = mealService.getMealsByTypeWithoutCertainMeals(mealType, mealsAlreadyAdded);

                    while(mealsAlreadyAdded.size() > 0 && meals.size()==0){
                        mealsAlreadyAdded.remove(0);
                        meals = mealService.getMealsByTypeWithoutCertainMeals(mealType, mealsAlreadyAdded);
                    }

                    if(meals.size()==0){
                        meals = mealService.getMealsByType(mealType);
                    }

                    MealDTO drawnMeal = meals.get((int) (Math.random() * (meals.size())));

                    menuMealDTOS.add(
                            MenuMealDTO.builder()
                                    .meal(drawnMeal)
                                    .dayNumber(i)
                                    .build());
                    if(mealType.equals(MealType.DINNER) && isOneDinnerForTwoDays){
                        lastDinner = drawnMeal;
                    }
                }



            }
        }
        return menuMealDTOS;
    }
}
