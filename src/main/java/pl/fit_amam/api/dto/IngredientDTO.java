package pl.fit_amam.api.dto;

import pl.fit_amam.api.enums.IngredientUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO extends AbstractBaseDto {

    private String name;

    private IngredientUnit ingredientUnit;
    
    private String category;
    
    @Builder
    public IngredientDTO(Long id, String userName, Timestamp creationDate, Timestamp updateDate, String name,
                         IngredientUnit ingredientUnit, String category) {
        super(id, userName, creationDate, updateDate);
        this.name = name;
        this.ingredientUnit = ingredientUnit;
        this.category = category;
    }
}
