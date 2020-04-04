package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.MenuMealDTO;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.MenuMealMapper;
import com.aws.codestar.projecttemplates.persistence.repositories.MenuDao;
import com.aws.codestar.projecttemplates.persistence.repositories.MenuMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuMealService {
    private MenuMealRepository menuMealRepository;
    private MenuMealMapper menuMealMapper;
    private MenuDao menuDao;

    @Autowired
    public MenuMealService(MenuMealRepository menuMealRepository,
                           MenuMealMapper menuMealMapper,
                           MenuDao menuDao) {
        this.menuMealRepository = menuMealRepository;
        this.menuMealMapper = menuMealMapper;
        this.menuDao = menuDao;
    }

    public MenuMealDTO create(MenuMealDTO menuMeal, Long menuId) throws ObjectIsNullException {
        validateMenuMealObject(menuMeal);
        validateMenuId(menuId);
        return menuMealMapper.toDTO(menuMealRepository.save(menuMealMapper.toEntity(menuMeal, menuId)));
    }

    @Transactional(readOnly = true)
    public MenuMealDTO get(Long id) {
        validateMenuMealId(id);
        return menuMealMapper.toDTO(menuMealRepository.findById(id).get());
    }

    public void delete(Long id) {
        validateMenuMealId(id);
        menuMealRepository.deleteById(id);
    }

    private void validateMenuMealId(Long menuMealId) {
        if (menuMealId == null || !menuMealRepository.existsById(menuMealId)) {
            throw new ObjectIdDoesNotExistsException(menuMealId);
        }
    }

    private void validateMenuId(Long menuId) {
        if (menuId == null || !menuDao.getRepository().existsById(menuId)) {
            throw new ObjectIdDoesNotExistsException(menuId, "menu");
        }
    }

    private void validateMenuMealObject(MenuMealDTO menuMeal) throws ObjectIsNullException {
        if (menuMeal == null) {
            throw new ObjectIsNullException(MenuMealDTO.class.getName());
        }
    }
}
