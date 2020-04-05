package pl.fit_amam.api.mappers;

import pl.fit_amam.api.persistence.repositories.MealRepository;
import pl.fit_amam.api.dto.MealIngredientDTO;
import pl.fit_amam.api.persistence.entities.MealIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealIngredientMapper {

    private IngredientMapper ingredientMapper;
    private MealRepository mealRepository;

    @Autowired
    public MealIngredientMapper(IngredientMapper ingredientMapper, MealRepository mealRepository) {
        this.ingredientMapper = ingredientMapper;
        this.mealRepository = mealRepository;
    }

    public MealIngredientDTO toDTO(MealIngredient mealIngredient) {
        if (mealIngredient == null) {
            return null;
        }

        return MealIngredientDTO.builder()
                .ingredient(ingredientMapper.toDTO(mealIngredient.getIngredient()))
                .amount(mealIngredient.getAmount())
                .build();
    }

    public MealIngredient toEntity(MealIngredientDTO mealIngredient, Long mealId) {
        if (mealIngredient == null) {
            return null;
        }

        return MealIngredient.builder()
                .meal(mealId == null ? null : mealRepository.findById(mealId).get())
                .ingredient(ingredientMapper.toEntity(mealIngredient.getIngredient()))
                .amount(mealIngredient.getAmount())
                .build();
    }

    public List<MealIngredientDTO> toDTOs(List<MealIngredient> mealIngredients) {
        if (mealIngredients == null) {
            return null;
        }

        return mealIngredients.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<MealIngredient> toEntities(List<MealIngredientDTO> mealIngredients, Long mealId) {
        if (mealIngredients == null) {
            return null;
        }

        return mealIngredients.stream().map(dto -> toEntity(dto, mealId)).collect(Collectors.toList());
    }
}
