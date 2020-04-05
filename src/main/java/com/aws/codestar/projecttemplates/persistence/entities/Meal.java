package com.aws.codestar.projecttemplates.persistence.entities;

import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "meals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal extends AbstractBaseEntity implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeOfMeal")
    private MealType typeOfMeal;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeOfPreparing")
    private PreparingType typeOfPreparing;

    @Column(name = "recipe")
    private String recipe;

    @Column(name = "minToPrepare")
    private Integer minutesToPrepare;

//    @OneToMany(mappedBy = "meal")
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.DETACH},
            mappedBy = "meal", orphanRemoval = true)
    private List<MealIngredient> mealIngredients;

    @Builder
    public Meal(String userName, Timestamp creationDate, Timestamp updateDate, Long id,
                String name, MealType typeOfMeal, PreparingType typeOfPreparing, String recipe,
                Integer minutesToPrepare,
                List<MealIngredient> mealIngredients) {
        super(userName, creationDate, updateDate);
        this.id = id;
        this.name = name;
        this.typeOfMeal = typeOfMeal;
        this.typeOfPreparing = typeOfPreparing;
        this.recipe = recipe;
        this.minutesToPrepare = minutesToPrepare;
        this.mealIngredients = mealIngredients;
    }
}
