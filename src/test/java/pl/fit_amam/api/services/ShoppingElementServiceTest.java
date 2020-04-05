package pl.fit_amam.api.services;

import pl.fit_amam.api.Application;
import pl.fit_amam.api.base.IngredientGenerator;
import pl.fit_amam.api.base.ShoppingElementGenerator;
import pl.fit_amam.api.base.ShoppingListGenerator;
import pl.fit_amam.api.controllers.config.H2JpaConfig;
import pl.fit_amam.api.dto.ShoppingElementDTO;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.ShoppingElementMapper;
import pl.fit_amam.api.persistence.entities.Ingredient;
import pl.fit_amam.api.persistence.entities.ShoppingElement;
import pl.fit_amam.api.persistence.entities.ShoppingList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class ShoppingElementServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ShoppingElementService shoppingElementService;

    @Autowired
    private ShoppingElementMapper shoppingElementMapper;

    @Test
    public void shouldReturnShoppingElementDTOWhenGetById() {
        // given
        entityManager.persist(createShoppingElement());
        entityManager.persist(createShoppingElement());

        // when
        List<ShoppingElement> shoppingElements =
                entityManager.createQuery("Select t from ShoppingElement t").getResultList();

        // then
        assertEquals(shoppingElements.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoShoppingElementsInDb() {
        // given
        // when
        List<ShoppingElement> shoppingElements =
                entityManager.createQuery("Select t from ShoppingElement t").getResultList();

        // then
        assertEquals(shoppingElements.size(), 0);
    }

    @Test
    public void shouldSaveShoppingElement() {
        // given
        ShoppingElement shoppingElementEntity = createShoppingElement();
        ShoppingElementDTO shoppingElementDTO = shoppingElementMapper.toDTO(shoppingElementEntity);

        // when
        this.shoppingElementService.create(shoppingElementDTO, shoppingElementEntity.getShoppingList().getId());
        List<ShoppingElement> savedShoppingElements =
                entityManager.createQuery("Select t from ShoppingElement t").getResultList();
        ShoppingElement savedShoppingElement = savedShoppingElements.get(0);

        // then
        assertEquals(savedShoppingElements.size(), 1);
        assertEquals(shoppingElementDTO.getAmount(), savedShoppingElement.getAmount());
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenSavedShoppingElementDTOMealIdIsNotInDb() {
        // given
        ShoppingElement shoppingElementEntity = createShoppingElement();
        ShoppingElementDTO shoppingElementDTO = shoppingElementMapper.toDTO(shoppingElementEntity);
        // when
        // then
        this.shoppingElementService.create(shoppingElementDTO, shoppingElementEntity.getShoppingList().getId() + 1);
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedShoppingElementDTOIsNull() {
        // given
        ShoppingElementDTO shoppingElementDTO = null;
        // when
        // then
        this.shoppingElementService.create(shoppingElementDTO, 11L);
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenSavedShoppingElementDTOMealIdIsNull() {
        // given
        ShoppingElement shoppingElementEntity = createShoppingElement();
        ShoppingElementDTO shoppingElementDTO = shoppingElementMapper.toDTO(shoppingElementEntity);

        // when
        // then
        this.shoppingElementService.create(shoppingElementDTO, null);
    }

    private ShoppingElement createShoppingElement() {
        ShoppingList shoppingList = ShoppingListGenerator.getSampleShoppingListEntity();
        shoppingList.setId(null);
        entityManager.persist(shoppingList);

        Ingredient ingredient = IngredientGenerator.getSampleIngredientEntity();
        ingredient.setId(null);
        entityManager.persist(ingredient);
        entityManager.flush();

        ShoppingElement shoppingElement = ShoppingElementGenerator.getSampleShoppingElementEntity();
        shoppingElement.setShoppingList(shoppingList);
        shoppingElement.setIngredient(ingredient);
        return shoppingElement;
    }
}