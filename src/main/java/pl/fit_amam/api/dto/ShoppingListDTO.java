package pl.fit_amam.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDTO extends AbstractBaseDto {

    private String name;

    private double numberOfPeople;

    private List<ShoppingElementDTO> shoppingElements;

    private boolean archival;

    @Builder
    public ShoppingListDTO(Long id, String userName, Timestamp creationDate, Timestamp updateDate,
                           String name, double numberOfPeople,
                           List<ShoppingElementDTO> shoppingElements, boolean archival) {
        super(id, userName, creationDate, updateDate);
        this.name = name;
        this.numberOfPeople = numberOfPeople;
        this.shoppingElements = shoppingElements;
        this.archival = archival;
    }
}
