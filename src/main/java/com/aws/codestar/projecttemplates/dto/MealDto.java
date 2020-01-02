package com.aws.codestar.projecttemplates.dto;

import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class MealDTO {
    private Long id;

    private String name;

    private MealType typeOfMeal;

    private PreparingType typeOfPreparing;

    private String recipe;

    private Integer minutesToPrepare;

    private List<MealIngredientDTO> ingredients;

}
