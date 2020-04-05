package com.aws.codestar.projecttemplates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO extends AbstractBaseDto {

    private double numberOfPeople;

    private String name;

    private List<MenuMealDTO> meals;

    private boolean archival;

    @Builder
    public MenuDTO(Long id, String userName, Timestamp creationDate, Timestamp updateDate,
                   double numberOfPeople, String name,
                   List<MenuMealDTO> meals, boolean archival) {
        super(id, userName, creationDate, updateDate);
        this.numberOfPeople = numberOfPeople;
        this.name = name;
        this.meals = meals;
        this.archival = archival;
    }
}
