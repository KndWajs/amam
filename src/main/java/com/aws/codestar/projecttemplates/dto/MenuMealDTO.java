package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MenuMealDTO {

    private MealDTO meal;

    private Integer dayNumber;

}