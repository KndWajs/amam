package pl.fit_amam.api.persistence.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.base.AbstractIntegrationTestBase;
import pl.fit_amam.api.base.IngredientGenerator;
import pl.fit_amam.api.enums.IngredientUnit;
import pl.fit_amam.api.persistence.entities.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
public class IngredientDaoTest extends AbstractIntegrationTestBase {
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