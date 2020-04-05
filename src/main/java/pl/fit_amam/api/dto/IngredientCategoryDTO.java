package pl.fit_amam.api.dto;

import pl.fit_amam.api.persistence.entities.EntityInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategoryDTO extends AbstractBaseDto implements EntityInterface {

    private String category;

    @Builder
    public IngredientCategoryDTO(Long id, String userName, Timestamp creationDate, Timestamp updateDate,
                                 String category) {
        super(id, userName, creationDate, updateDate);
        this.category = category;
    }
}
