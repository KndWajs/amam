package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.IngredientGenerator;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.dto.IngredientDTO;
import com.aws.codestar.projecttemplates.exceptions.EmptyRequiredFieldException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIdDoesNotExistsException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.IngredientMapper;
import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
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
public class IngredientServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientMapper ingredientMapper;

    @Test
    public void shouldReturnTwoIngredientsDTOWhenGetAllNotArchivalIngredients() {
        // given
        entityManager.persist(createIngredient());
        entityManager.persist(createIngredient());

        // when
        List<IngredientDTO> ingredients = this.ingredientService.getAll();

        // then
        assertEquals(ingredients.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoIngredientsInDb() {
        // given
        // when
        List<IngredientDTO> ingredients = this.ingredientService.getAll();

        // then
        assertEquals(ingredients.size(), 0);
    }

    @Test
    public void shouldSaveIngredient() {
        // given
        IngredientDTO ingredientDTO = ingredientMapper.toDTO(createIngredient());

        // when
        IngredientDTO ingredient = this.ingredientService.create(ingredientDTO);
        List<IngredientDTO> savedIngredients = this.ingredientService.getAll();
        IngredientDTO savedIngredient = this.ingredientService.get(ingredient.getId());

        // then
        assertEquals(savedIngredients.size(), 1);
        assertEquals(ingredientDTO.getName(), savedIngredient.getName());
    }

    @Test
    public void shouldSaveIngredientWhenIdIsNotNull() {
        // given
        IngredientDTO ingredientDTO = ingredientMapper.toDTO(createIngredient());
        ingredientDTO.setId(323L);

        // when
        IngredientDTO ingredient = this.ingredientService.create(ingredientDTO);
        List<IngredientDTO> savedIngredients = this.ingredientService.getAll();
        IngredientDTO savedIngredient = this.ingredientService.get(ingredient.getId());

        // then
        assertEquals(savedIngredients.size(), 1);
        assertEquals(ingredientDTO.getName(), savedIngredient.getName());
    }

    @Test
    public void shouldReturnIngredientDTOWhenUpdate() {
        // given
        IngredientDTO ingredientDTO =
                this.ingredientService.create(ingredientMapper.toDTO(createIngredient()));

        // when
        IngredientDTO updatedIngredient = this.ingredientService.update(ingredientDTO);

        // then
        assertEquals(ingredientDTO, updatedIngredient);
    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    public void shouldThrowExceptionWhenUpdateIngredientDTOIdIsNotInDb() {
        // given
        IngredientDTO ingredientDTO =
                this.ingredientService.create(ingredientMapper.toDTO(createIngredient()));

        // when
        // then
        ingredientDTO.setId(ingredientDTO.getId() + 1);
        this.ingredientService.update(ingredientDTO);
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedIngredientDTOIsNull() {
        // given
        IngredientDTO ingredientDTO = null;
        // when
        // then
        this.ingredientService.create(ingredientDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedIngredientDTOHasEmptyNameString() {
        // given
        IngredientDTO ingredientDTO = ingredientMapper.toDTO(createIngredient());
        ingredientDTO.setName("");

        // when
        // then
        this.ingredientService.create(ingredientDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedIngredientDTOHasNullInNameString() {
        // given
        IngredientDTO ingredientDTO = ingredientMapper.toDTO(createIngredient());
        ingredientDTO.setName(null);

        // when
        // then
        this.ingredientService.create(ingredientDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedIngredientDTOHasNullInIngredientUnitString() {
        // given
        IngredientDTO ingredientDTO = ingredientMapper.toDTO(createIngredient());
        ingredientDTO.setIngredientUnit(null);

        // when
        // then
        this.ingredientService.create(ingredientDTO);
    }

    private Ingredient createIngredient() {
        Ingredient ingredient = IngredientGenerator.getSampleIngredientEntity();
        return ingredient;
    }
}