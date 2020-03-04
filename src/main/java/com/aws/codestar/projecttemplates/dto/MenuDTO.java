package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private Long id;

    private double numberOfPeople;

    private String name;

    private List<MenuMealDTO> meals;

    private boolean archival;
}
