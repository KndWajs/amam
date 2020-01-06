package com.aws.codestar.projecttemplates.dto;

import com.aws.codestar.projecttemplates.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class MenuParametersDTO {
    private String name;
    private int numberOfDays;
    private int numberOfPersons;
    private List<MealType> mealTypes;
}
