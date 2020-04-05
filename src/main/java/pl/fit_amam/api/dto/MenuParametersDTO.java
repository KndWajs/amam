package pl.fit_amam.api.dto;

import pl.fit_amam.api.enums.MealType;
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
    private boolean isDinnerForTwoDays;
    private List<MealType> mealTypes;
}
