package pl.fit_amam.api.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "shoppingLists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingList extends AbstractBaseEntity implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "numberOfPeople")
    private double numberOfPeople;

    @Column(name = "archival")
    private boolean archival;

    @OneToMany(
            cascade = {CascadeType.ALL}
            ,mappedBy = "shoppingList"
            ,orphanRemoval = true
    )
    private List<ShoppingElement> shoppingElements;

    @Builder
    public ShoppingList(String userName, Timestamp creationDate, Timestamp updateDate, Long id,
                        String name, double numberOfPeople, boolean archival,
                        List<ShoppingElement> shoppingElements) {
        super(userName, creationDate, updateDate);
        this.id = id;
        this.name = name;
        this.numberOfPeople = numberOfPeople;
        this.archival = archival;
        this.shoppingElements = shoppingElements;
    }
}
