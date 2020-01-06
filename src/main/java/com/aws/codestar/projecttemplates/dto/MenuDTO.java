package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class MenuDTO {
    private Long id;

    private int numberOfPeople;

    private String name;

    private List<MenuMealDTO> meals;
}
