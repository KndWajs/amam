package pl.fit_amam.api.services;

import pl.fit_amam.api.Application;
import pl.fit_amam.api.base.IngredientCategoryGenerator;
import pl.fit_amam.api.controllers.config.H2JpaConfig;
import pl.fit_amam.api.dto.IngredientCategoryDTO;
import pl.fit_amam.api.exceptions.EmptyRequiredFieldException;
import pl.fit_amam.api.exceptions.ObjectIdDoesNotExistsException;
import pl.fit_amam.api.exceptions.ObjectIsNullException;
import pl.fit_amam.api.mappers.IngredientCategoryMapper;
import pl.fit_amam.api.persistence.entities.IngredientCategory;
import org.junit.Ignore;
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
public class IngredientCategoryServiceTest {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private IngredientCategoryService ingredientCategoryService;

    @Autowired
    private IngredientCategoryMapper ingredientCategoryMapper;

    @Test
    public void shouldReturnTwoIngredientCategoriesDTOWhenGetAllIngredientCategories() {
        // given
        entityManager.persist(createIngredientCategory());
        entityManager.persist(createIngredientCategory());

        // when
        List<IngredientCategoryDTO> ingredientCategories = this.ingredientCategoryService.getAll();

        // then
        assertEquals(ingredientCategories.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoIngredientCategoriesInDb() {
        // given
        // when
        List<IngredientCategoryDTO> ingredientCategories = this.ingredientCategoryService.getAll();

        // then
        assertEquals(ingredientCategories.size(), 0);
    }

    @Test
    public void shouldSaveIngredientCategory() {
        // given
        IngredientCategoryDTO ingredientCategoryDTO = ingredientCategoryMapper.toDTO(createIngredientCategory());

        // when
        this.ingredientCategoryService.create(ingredientCategoryDTO);
        List<IngredientCategoryDTO> savedIngredientCategories =
                this.ingredientCategoryService.getAll();
        IngredientCategoryDTO savedIngredientCategory = savedIngredientCategories.get(0);

        // then
        assertEquals(savedIngredientCategories.size(), 1);
        assertEquals(ingredientCategoryDTO.getCategory(), savedIngredientCategory.getCategory());
    }

    @Test
    public void shouldSaveIngredientCategoryWhenIdIsNotNull() {
        // given
        IngredientCategoryDTO ingredientCategoryDTO = ingredientCategoryMapper.toDTO(createIngredientCategory());
        ingredientCategoryDTO.setId(323L);

        // when
        this.ingredientCategoryService.create(ingredientCategoryDTO);
        List<IngredientCategoryDTO> savedIngredientCategories =
                this.ingredientCategoryService.getAll();
        IngredientCategoryDTO savedIngredientCategory = savedIngredientCategories.get(0);

        // then
        assertEquals(savedIngredientCategories.size(), 1);
        assertEquals(ingredientCategoryDTO.getCategory(), savedIngredientCategory.getCategory());
    }

    @Test
    @Ignore
    public void shouldReturnIngredientCategoryDTOWhenUpdate() {

    }

    @Test(expected = ObjectIdDoesNotExistsException.class)
    @Ignore
    public void shouldThrowExceptionWhenGetIngredientCategoryDTOIdIsNotInDb() {
    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedIngredientCategoryDTOIsNull() {
        // given
        IngredientCategoryDTO ingredientCategoryDTO = null;
        // when
        // then
        this.ingredientCategoryService.create(ingredientCategoryDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedIngredientCategoryDTOHasEmptyCategoryString() {
        // given
        IngredientCategoryDTO ingredientCategoryDTO = ingredientCategoryMapper.toDTO(createIngredientCategory());
        ingredientCategoryDTO.setCategory("");

        // when
        // then
        this.ingredientCategoryService.create(ingredientCategoryDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedIngredientCategoryDTOHasNullInCategoryString() {
        // given
        IngredientCategoryDTO ingredientCategoryDTO = ingredientCategoryMapper.toDTO(createIngredientCategory());
        ingredientCategoryDTO.setCategory(null);

        // when
        // then
        this.ingredientCategoryService.create(ingredientCategoryDTO);
    }

    private IngredientCategory createIngredientCategory() {
        return IngredientCategoryGenerator.getSampleIngredientCategoryEntity();
    }
}