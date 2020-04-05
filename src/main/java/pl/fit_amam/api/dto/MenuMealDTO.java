package pl.fit_amam.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuMealDTO {

    private Long id;

    private MealDTO meal;

    private Integer dayNumber;

}
