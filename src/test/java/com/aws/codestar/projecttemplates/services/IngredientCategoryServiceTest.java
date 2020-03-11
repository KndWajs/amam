package com.aws.codestar.projecttemplates.services;

import com.aws.codestar.projecttemplates.exceptions.EmptyRequiredFieldException;
import com.aws.codestar.projecttemplates.exceptions.ObjectIsNullException;
import com.aws.codestar.projecttemplates.mappers.IngredientCategoryMapper;
import com.aws.codestar.projecttemplates.persistence.repositories.IngredientCategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientCategoryServiceTest {
    @InjectMocks
    private IngredientCategoryService ingredientCategoryService;

    @Mock
    private IngredientCategoryRepository ingredientCategoryRepository;
    @Mock
    private IngredientCategoryMapper ingredientCategoryMapper;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldReturnTwoIngredientsCategoryDTOWhenGetAllIngredientCategories() {

    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoIngredientCategoriesInDb() {

    }

    @Test
    public void shouldSendToDbIngredientCategory() {

    }

    @Test
    public void shouldSendToDbIngredientCategoryWithNullInId() {

    }

    @Test
    public void shouldReturnIngredientCategoryDTOWhenSavingInDbReturnEntity() {

    }

    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedIngredientCategoryDTOIsNull() {

    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedIngredientCategoryDTOHasEmptyCategoryString() {

    }

}