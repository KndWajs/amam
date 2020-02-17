package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.enums.IngredientUnit;
import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.entities.IngredientCategory;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
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
    protected EntityManager entityManager;

    @Autowired
    private IngredientDao ingredientDao;

    @Test
    public void shouldFindOneIngredientContainingSpecificStringInName() {
        // given
        Ingredient ingredient = getSampleIngredient();
        entityManager.persist(ingredient);

        // when
        List<Ingredient> searchResult = ingredientDao.getIngredientsByPartialName("carr", 10);

        // then
        assertEquals(1, searchResult.size());
    }

    @Test
    public void shouldFindIngredientWithSpecificNameAndUnit() {
        // given
        Ingredient ingredient = getSampleIngredient();
        entityManager.persist(ingredient);
        Ingredient secondIngredient = getSampleIngredient();
        secondIngredient.setIngredientUnit(IngredientUnit.ML_OR_GRAM);
        entityManager.persist(ingredient);

        Ingredient searchedIngredient = getSampleIngredient();

        // when
        Ingredient searchResult = ingredientDao.getIngredientByNameAndUnit(searchedIngredient);

        // then
        assertNotNull(searchResult);
    }

    private Ingredient getSampleIngredient() {
        return Ingredient.builder()
                .name("carrot")
                .ingredientUnit(IngredientUnit.PCS)
                .category("sery")
                .build();
    }
}