package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.Application;
import pl.fit_amam.api.base.ShoppingListGenerator;
import pl.fit_amam.api.controllers.config.H2JpaConfig;
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
public class ShoppingListDaoTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ShoppingListDao shoppingListDao;

    @Test
    public void shouldFindOneShoppingListWhichArchivalIsTrue() {
        // given
        boolean isArchival = true;
        ShoppingList shoppingList = ShoppingListGenerator.getSampleShoppingListEntity();
        shoppingList.setId(null);
        shoppingList.setArchival(isArchival);
        entityManager.persist(shoppingList);
        ShoppingList secondShoppingList = ShoppingListGenerator.getSampleShoppingListEntity();
        secondShoppingList.setId(null);
        secondShoppingList.setArchival(!isArchival);
        entityManager.persist(secondShoppingList);

        // when
        List<ShoppingList> searchResult = shoppingListDao.getShoppingListsByArchivalStatus(isArchival);

        // then
        assertEquals(1, searchResult.size());
    }

    @Test
    public void shouldNotFindShoppingListWhichArchivalIsTrue() {
        // given
        boolean isArchival = false;
        ShoppingList shoppingList = ShoppingListGenerator.getSampleShoppingListEntity();
        shoppingList.setId(null);
        shoppingList.setArchival(isArchival);

        // when
        List<ShoppingList> searchResult = shoppingListDao.getShoppingListsByArchivalStatus(!isArchival);

        // then
        assertEquals(0, searchResult.size());
    }

}