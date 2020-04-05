package com.aws.codestar.projecttemplates.dto;

import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealDTO extends AbstractBaseDto {

    private String name;

    private MealType typeOfMeal;

    private PreparingType typeOfPreparing;

    private String recipe;

    private Integer minutesToPrepare;

    private List<MealIngredientDTO> ingredients;

    @Builder
    public MealDTO(Long id, String userName, Timestamp creationDate, Timestamp updateDate,
                   String name, MealType typeOfMeal, PreparingType typeOfPreparing, String recipe,
                   Integer minutesToPrepare,
                   List<MealIngredientDTO> ingredients) {
        super(id, userName, creationDate, updateDate);
        this.name = name;
        this.typeOfMeal = typeOfMeal;
        this.typeOfPreparing = typeOfPreparing;
        this.recipe = recipe;
        this.minutesToPrepare = minutesToPrepare;
        this.ingredients = ingredients;
    }
}
