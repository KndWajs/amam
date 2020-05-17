package pl.fit_amam.api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.dto.ShoppingElementDTO;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.ShoppingElementMapper;
import pl.fit_amam.api.persistence.entities.ShoppingElement;
import pl.fit_amam.api.persistence.repositories.ShoppingElementRepository;
import pl.fit_amam.api.persistence.repositories.ShoppingListDao;

import java.sql.Timestamp;

@Service
@Transactional
public class ShoppingElementService {
    private ShoppingElementRepository shoppingElementRepository;
    private ShoppingElementMapper shoppingElementMapper;
    private ShoppingListDao shoppingListDao;

    @Autowired
    public ShoppingElementService(
            ShoppingElementRepository shoppingElementRepository,
            ShoppingElementMapper shoppingElementMapper,
            ShoppingListDao shoppingListDao) {
        this.shoppingElementRepository = shoppingElementRepository;
        this.shoppingElementMapper = shoppingElementMapper;
        this.shoppingListDao = shoppingListDao;
    }

    public ShoppingElementDTO create(ShoppingElementDTO shoppingElementDTO, Long shoppingListId)
            throws ObjectIsNullException {
        validateShoppingElementObject(shoppingElementDTO);
        validateShoppingListId(shoppingListId);

        ShoppingElement shoppingElement = shoppingElementMapper.toEntity(shoppingElementDTO, shoppingListId);
        shoppingElement.setCreationDate(new Timestamp(System.currentTimeMillis()));
        shoppingElement.setUserName(UserService.getUserName());

        return shoppingElementMapper
                .toDTO(shoppingElementRepository.save(shoppingElement));
    }

    @Transactional(readOnly = true)
    public ShoppingElementDTO get(Long id) {
        validateShoppingElementId(id);
        return shoppingElementMapper.toDTO(shoppingElementRepository.findById(id).get());
    }

    public ShoppingElementDTO update(ShoppingElementDTO shoppingElementDTO, Long shoppingListId) {
        validateShoppingElementObject(shoppingElementDTO);
        validateShoppingElementId(shoppingElementDTO.getId());

        ShoppingElement shoppingElement = shoppingElementMapper.toEntity(shoppingElementDTO, shoppingListId);
        shoppingElement.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        return shoppingElementMapper.toDTO(shoppingElementRepository.save(shoppingElement));
    }

    public void delete(Long id) {
        validateShoppingElementId(id);
        shoppingElementRepository.deleteById(id);
    }

    private void validateShoppingElementId(Long shoppingElementId) {
        if (shoppingElementId == null || !shoppingElementRepository.existsById(shoppingElementId)) {
            throw new ObjectIdDoesNotExistsException(shoppingElementId);
        }
    }

    private void validateShoppingListId(Long shoppingListId) {
        if (shoppingListId == null || !shoppingListDao.getRepository().existsById(shoppingListId)) {
            throw new ObjectIdDoesNotExistsException(shoppingListId, "shoppingList");
        }
    }

    private void validateShoppingElementObject(ShoppingElementDTO shoppingElement) throws ObjectIsNullException {
        if (shoppingElement == null) {
            throw new ObjectIsNullException(ShoppingElementDTO.class.getName());
        }
    }
}
