package com.aws.codestar.projecttemplates.services;


import com.aws.codestar.projecttemplates.dto.ShoppingElementDTO;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.ShoppingElementMapper;
import com.aws.codestar.projecttemplates.persistence.repositories.ShoppingElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoppingElementService {
    private ShoppingElementRepository shoppingElementRepository;
    private ShoppingElementMapper shoppingElementMapper;

    @Autowired
    public ShoppingElementService(ShoppingElementRepository shoppingElementRepository, ShoppingElementMapper shoppingElementMapper) {
        this.shoppingElementRepository = shoppingElementRepository;
        this.shoppingElementMapper = shoppingElementMapper;
    }

    public ShoppingElementDTO create(ShoppingElementDTO shoppingElement, Long shoppingListId) throws ObjectIsNullException {
        validateShoppingElementObject(shoppingElement);

        return shoppingElementMapper.toDTO(shoppingElementRepository.save(shoppingElementMapper.toEntity(shoppingElement, shoppingListId)));
    }

    @Transactional(readOnly = true)
    public ShoppingElementDTO get(Long id) {
        validateShoppingElementId(id);
        return shoppingElementMapper.toDTO(shoppingElementRepository.findById(id).get());
    }

//    public ShoppingElementDTO update(ShoppingElementDTO shoppingElement) throws ObjectIsNullException {
//        validateShoppingElementObject(shoppingElement);
//        return shoppingElementMapper.toDTO(shoppingElementRepository.saveAndFlush(shoppingElementMapper.toEntity(shoppingElement)));
//    }

    public void delete(Long id) {
        validateShoppingElementId(id);
        shoppingElementRepository.deleteById(id);
    }

    public ShoppingElementDTO update(ShoppingElementDTO shoppingElementDTO, Long shoppingListId) {
        validateShoppingElementObject(shoppingElementDTO);
        validateShoppingElementId(shoppingElementDTO.getId());
        return shoppingElementMapper.toDTO(shoppingElementRepository.save(shoppingElementMapper.toEntity(shoppingElementDTO, shoppingListId)));
    }

    private void validateShoppingElementId(Long shoppingElementId) {
        if (shoppingElementId == null || !shoppingElementRepository.existsById(shoppingElementId)) {
            throw new ObjectIdDoesNotExistsException(shoppingElementId);
        }
    }

    private void validateShoppingElementObject(ShoppingElementDTO shoppingElement) throws ObjectIsNullException {
        if (shoppingElement == null) {
            throw new ObjectIsNullException(ShoppingElementDTO.class.getName());
        }
    }
}
