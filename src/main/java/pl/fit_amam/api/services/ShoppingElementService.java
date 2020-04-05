package pl.fit_amam.api.services;


import pl.fit_amam.api.dto.ShoppingElementDTO;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.ShoppingElementMapper;
import pl.fit_amam.api.persistence.repositories.ShoppingElementRepository;
import pl.fit_amam.api.persistence.repositories.ShoppingListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ShoppingElementDTO create(ShoppingElementDTO shoppingElement, Long shoppingListId)
            throws ObjectIsNullException {
        validateShoppingElementObject(shoppingElement);
        validateShoppingListId(shoppingListId);
        return shoppingElementMapper
                .toDTO(shoppingElementRepository.save(shoppingElementMapper.toEntity(shoppingElement, shoppingListId)));
    }

    @Transactional(readOnly = true)
    public ShoppingElementDTO get(Long id) {
        validateShoppingElementId(id);
        return shoppingElementMapper.toDTO(shoppingElementRepository.findById(id).get());
    }

    public ShoppingElementDTO update(ShoppingElementDTO shoppingElementDTO, Long shoppingListId) {
        validateShoppingElementObject(shoppingElementDTO);
        validateShoppingElementId(shoppingElementDTO.getId());
        return shoppingElementMapper.toDTO(shoppingElementRepository
                .save(shoppingElementMapper.toEntity(shoppingElementDTO, shoppingListId)));
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
