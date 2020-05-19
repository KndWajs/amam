package pl.fit_amam.api.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.base.IngredientGenerator;
import pl.fit_amam.api.base.ShoppingElementGenerator;
import pl.fit_amam.api.base.ShoppingListGenerator;
import pl.fit_amam.api.dto.ShoppingListDTO;
import pl.fit_amam.api.exceptions.EmptyRequiredFieldException;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.ShoppingListMapper;
import pl.fit_amam.api.persistence.entities.Ingredient;
import pl.fit_amam.api.persistence.entities.ShoppingElement;
import pl.fit_amam.api.persistence.entities.ShoppingList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class ShoppingListServiceTest extends ServiceTestBase {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private ShoppingListMapper shoppingListMapper;

    @Test
    public void shouldReturnTwoShoppingListsDTOWhenGetAllNotArchivalShoppingLists() {
        // given
        entityManager.persist(createShoppingList());
        entityManager.persist(createShoppingList());

        // when
        List<ShoppingListDTO> shoppingLists = this.shoppingListService.getAll(false);

        // then
        assertEquals(shoppingLists.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoShoppingListsInDb() {
        // given
        // when
        List<ShoppingListDTO> shoppingLists = this.shoppingListService.getAll(false);

        // then
        assertEquals(shoppingLists.size(), 0);
    }

    @Test
    public void shouldSaveShoppingList() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createShoppingList());

        // when
        ShoppingListDTO shoppingList = this.shoppingListService.create(shoppingListDTO);
        List<ShoppingListDTO> savedShoppingLists = this.shoppingListService.getAll(false);
        ShoppingListDTO savedShoppingList = this.shoppingListService.get(shoppingList.getId());

        // then
        assertEquals(savedShoppingLists.size(), 1);
        assertEquals(shoppingListDTO.getName(), savedShoppingList.getName());
    }

    @Test
    public void shouldSaveShoppingListWithoutShoppingElements() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createShoppingList());
        shoppingListDTO.setShoppingElements(null);

        // when
        ShoppingListDTO shoppingList = this.shoppingListService.create(shoppingListDTO);
        List<ShoppingListDTO> savedShoppingLists = this.shoppingListService.getAll(false);
        ShoppingListDTO savedShoppingList = this.shoppingListService.get(shoppingList.getId());

        // then
        assertEquals(savedShoppingLists.size(), 1);
        assertEquals(shoppingListDTO.getName(), savedShoppingList.getName());
    }

    @Test
    public void shouldSaveShoppingListWhenIdIsNotNull() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createShoppingList());
        shoppingListDTO.setId(323L);

        // when
        ShoppingListDTO shoppingList = this.shoppingListService.create(shoppingListDTO);
        List<ShoppingListDTO> savedShoppingLists = this.shoppingListService.getAll(false);
        ShoppingListDTO savedShoppingList = this.shoppingListService.get(shoppingList.getId());

        // then
        assertEquals(savedShoppingLists.size(), 1);
        assertEquals(shoppingListDTO.getName(), savedShoppingList.getName());
    }

    @Test
    public void shouldReturnShoppingListDTOWhenUpdate() {
        // given
        ShoppingListDTO shoppingListDTO =
                this.shoppingListService.create(shoppingListMapper.toDTO(createShoppingList()));

        // when
        ShoppingListDTO updatedShoppingList = this.shoppingListService.update(shoppingListDTO);

        // then
        assertEquals(shoppingListDTO, updatedShoppingList);
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenUpdateShoppingListDTOIdIsNotInDb() {
        // given
        ShoppingListDTO shoppingListDTO =
                this.shoppingListService.create(shoppingListMapper.toDTO(createShoppingList()));

        // when
        // then
        shoppingListDTO.setId(shoppingListDTO.getId() + 1);
        this.shoppingListService.update(shoppingListDTO);
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedShoppingListDTOIsNull() {
        // given
        ShoppingListDTO shoppingListDTO = null;
        // when
        // then
        this.shoppingListService.create(shoppingListDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedShoppingListDTOHasEmptyNameString() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createShoppingList());
        shoppingListDTO.setName("");

        // when
        // then
        this.shoppingListService.create(shoppingListDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedShoppingListDTOHasNullInNameString() {
        // given
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDTO(createShoppingList());
        shoppingListDTO.setName(null);

        // when
        // then
        this.shoppingListService.create(shoppingListDTO);
    }

    private ShoppingList createShoppingList() {
        ShoppingList shoppingList = ShoppingListGenerator.getSampleShoppingListEntity();
        ShoppingElement shoppingElement = ShoppingElementGenerator.getSampleShoppingElementEntity();
        Ingredient ingredient = IngredientGenerator.getSampleIngredientEntity();
        entityManager.persist(ingredient);
        shoppingElement.setIngredient(ingredient);
        shoppingList.setShoppingElements(new ArrayList<>());
        shoppingList.getShoppingElements().add(shoppingElement);
        return shoppingList;
    }
}