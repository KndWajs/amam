package pl.fit_amam.api.persistence.entities;

import pl.fit_amam.api.enums.IngredientUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient extends AbstractBaseEntity implements EntityInterface, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private IngredientUnit ingredientUnit;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "ingredient")
    private List<MealIngredient> mealIngredients;

    @Builder
    public Ingredient(String userName, Timestamp creationDate, Timestamp updateDate, Long id,
                      String name, IngredientUnit ingredientUnit, String category,
                      List<MealIngredient> mealIngredients) {
        super(userName, creationDate, updateDate);
        this.id = id;
        this.name = name;
        this.ingredientUnit = ingredientUnit;
        this.category = category;
        this.mealIngredients = mealIngredients;
    }
}
