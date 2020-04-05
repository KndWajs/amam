package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.Application;
import pl.fit_amam.api.base.IngredientGenerator;
import pl.fit_amam.api.controllers.config.H2JpaConfig;
import pl.fit_amam.api.enums.IngredientUnit;
import pl.fit_amam.api.persistence.entities.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class IngredientDaoTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IngredientDao ingredientDao;

    @Test
    public void shouldFindOneIngredientContainingSpecificStringInName() {
        // given
        Ingredient ingredient = IngredientGenerator.getSampleIngredientEntity();
        entityManager.persist(ingredient);

        // when
        List<Ingredient> searchResult = ingredientDao.getIngredientsByPartialName("carr", 10);

        // then
        assertEquals(1, searchResult.size());
    }

    @Test
    public void shouldFindIngredientWithSpecificNameAndUnit() {
        // given
        Ingredient ingredient = IngredientGenerator.getSampleIngredientEntity();
        entityManager.persist(ingredient);
        Ingredient secondIngredient = IngredientGenerator.getSampleIngredientEntity();
        secondIngredient.setIngredientUnit(IngredientUnit.ML_OR_GRAM);
        entityManager.persist(ingredient);

        Ingredient searchedIngredient = IngredientGenerator.getSampleIngredientEntity();

        // when
        Ingredient searchResult = ingredientDao.getIngredientByNameAndUnit(searchedIngredient);

        // then
        assertNotNull(searchResult);
    }
}