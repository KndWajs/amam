package pl.fit_amam.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingElementDTO extends AbstractBaseDto {

    private IngredientDTO ingredient;

    private Double amount;

    private boolean alreadyBought;

    @Builder
    public ShoppingElementDTO(Long id, String userName, Timestamp creationDate, Timestamp updateDate,
                              IngredientDTO ingredient, Double amount, boolean alreadyBought) {
        super(id, userName, creationDate, updateDate);
        this.ingredient = ingredient;
        this.amount = amount;
        this.alreadyBought = alreadyBought;
    }
}
