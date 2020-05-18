package pl.fit_amam.api.services;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.dto.MealDTO;
import pl.fit_amam.api.dto.MenuDTO;
import pl.fit_amam.api.dto.MenuMealDTO;
import pl.fit_amam.api.dto.MenuParametersDTO;
import pl.fit_amam.api.enums.MealType;
import pl.fit_amam.api.exceptions.EmptyRequiredFieldException;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.MenuMapper;
import pl.fit_amam.api.persistence.entities.Menu;
import pl.fit_amam.api.persistence.repositories.MenuDao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class MenuService {
    private MenuMapper menuMapper;
    private MenuMealService menuMealService;
    private MealService mealService;
    private MenuDao menuDao;

    @Autowired
    public MenuService(MenuMapper menuMapper, MenuMealService menuMealService,
                       MealService mealService, MenuDao menuDao) {
        this.menuMapper = menuMapper;
        this.menuMealService = menuMealService;
        this.mealService = mealService;
        this.menuDao = menuDao;
    }

    public MenuDTO create(MenuDTO menuDTO) throws ObjectIsNullException {
        validateMenuObject(menuDTO);
        List<MenuMealDTO> menuMeals = menuDTO.getMeals();

        menuDTO.setMeals(new ArrayList<>());
        Menu menu = menuMapper.toEntity(menuDTO);
        menu.setCreationDate(new Timestamp(System.currentTimeMillis()));
        menu.setUserName(UserService.getUserName());

        MenuDTO savedMenu = menuMapper.toDTO(menuDao.getRepository().save(menu));

        if(!(menuMeals == null || menuMeals.isEmpty())){
            for (MenuMealDTO menuMeal : menuMeals) {
                savedMenu.getMeals().add(menuMeal);
            }
        }

        return update(savedMenu);
    }

    public MenuDTO createMenuProposal(MenuParametersDTO menuParametersDTO) {
        return MenuDTO.builder()
                .id(null)
                .numberOfPeople(menuParametersDTO.getNumberOfPersons())
                .name(menuParametersDTO.getName())
                .meals(createRandomMenu(menuParametersDTO.getNumberOfDays(), menuParametersDTO.getMealTypes(),
                        menuParametersDTO.isDinnerForTwoDays()))
                .build();
    }

    @Transactional(readOnly = true)
    public MenuDTO get(Long id) {
        validateMenuId(id);
        return menuMapper.toDTO(menuDao.getRepository().findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<MenuDTO> getAll() {
        List<MenuDTO> menus = new ArrayList<>();
        for (Menu menu : menuDao.getRepository().findAll()) {
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

    public MenuDTO update(MenuDTO menuDTO) throws ObjectIsNullException {
        validateMenuObject(menuDTO);
        validateMenuId(menuDTO.getId());

        Menu menu = menuMapper.toEntity(menuDTO);
        menu.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        MenuDTO savedMenu = menuMapper.toDTO(menuDao.getRepository().save(menu));
        return savedMenu;
    }

    public void delete(Long id) {
        validateMenuId(id);
        menuDao.getRepository().deleteById(id);
    }

    private void validateMenuId(Long menuId) {
        if (menuId == null || !menuDao.getRepository().existsById(menuId)) {
            throw new ObjectIdDoesNotExistsException(menuId);
        }
    }

    private void validateMenuObject(MenuDTO menu) throws ObjectIsNullException {
        if (menu == null) {
            throw new ObjectIsNullException(MenuDTO.class.getName());
        }
        if (StringUtils.isEmpty(menu.getName())) {
            throw new EmptyRequiredFieldException("Name");
        }
    }

    private List<MenuMealDTO> createRandomMenu(int numberOfDays, List<MealType> mealTypes,
                                               boolean isOneDinnerForTwoDays) {

        List<MenuMealDTO> menuMealDTOS = new ArrayList<>();
        MealDTO lastDinner = new MealDTO();

        for (int i = 1; i <= numberOfDays; i++) {
            for (MealType mealType : mealTypes) {
                if (mealType.equals(MealType.DINNER) && isOneDinnerForTwoDays && i % 2 == 0) {
                    menuMealDTOS.add(
                            MenuMealDTO.builder()
                                    .meal(lastDinner)
                                    .dayNumber(i)
                                    .build());
                } else {
                    List<MealDTO> mealsAlreadyAdded = new LinkedList<>();
                    for (MenuMealDTO menuMealDTO : menuMealDTOS) {
                        mealsAlreadyAdded.add(menuMealDTO.getMeal());
                    }

                    List<MealDTO> meals = mealService.getMealsByTypeWithoutCertainMeals(mealType, mealsAlreadyAdded);

                    while (mealsAlreadyAdded.size() > 0 && meals.size() == 0) {
                        mealsAlreadyAdded.remove(0);
                        meals = mealService.getMealsByTypeWithoutCertainMeals(mealType, mealsAlreadyAdded);
                    }

                    if (meals.size() == 0) {
                        meals = mealService.getMealsByType(mealType);
                    }

                    MealDTO drawnMeal = meals.get((int) (Math.random() * (meals.size())));

                    menuMealDTOS.add(
                            MenuMealDTO.builder()
                                    .meal(drawnMeal)
                                    .dayNumber(i)
                                    .build());
                    if (mealType.equals(MealType.DINNER) && isOneDinnerForTwoDays) {
                        lastDinner = drawnMeal;
                    }
                }


            }
        }
        return menuMealDTOS;
    }
}
