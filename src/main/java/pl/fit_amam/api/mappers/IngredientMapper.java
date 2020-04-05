package pl.fit_amam.api.mappers;

import pl.fit_amam.api.dto.IngredientDTO;
import pl.fit_amam.api.persistence.entities.Ingredient;
import pl.fit_amam.api.persistence.repositories.MealIngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper implements Mapper<IngredientDTO, Ingredient> {

    private MealIngredientDao mealIngredientDao;

    @Autowired
    public IngredientMapper(MealIngredientDao mealIngredientDao) {
        this.mealIngredientDao = mealIngredientDao;
    }

    @Override
    public IngredientDTO toDTO(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        return IngredientDTO.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .ingredientUnit(ingredient.getIngredientUnit())
                .category(ingredient.getCategory())
                .userName(ingredient.getUserName())
                .creationDate(ingredient.getCreationDate())
                .updateDate(ingredient.getUpdateDate())
                .build();
    }

    @Override
    public Ingredient toEntity(IngredientDTO ingredient) {
        if (ingredient == null) {
            return null;
        }

        return Ingredient.builder()
                .id(ingredient.getId())
                .name(ingredient.getName() == null ? null : ingredient.getName().toLowerCase())
                .ingredientUnit(ingredient.getIngredientUnit())
                .mealIngredients(ingredient.getId() == null ? null : mealIngredientDao.getMealIngredientsByIngredientId(ingredient.getId()))
                .category(ingredient.getCategory())
                .userName(ingredient.getUserName())
                .creationDate(ingredient.getCreationDate())
                .updateDate(ingredient.getUpdateDate())
                .build();
    }
}
