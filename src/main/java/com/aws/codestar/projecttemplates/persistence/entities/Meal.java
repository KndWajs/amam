package com.aws.codestar.projecttemplates.persistence.entities;

import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meals")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meal implements EntityInterface {
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

    @OneToMany(mappedBy = "meal")
    private List<MealIngredient> mealIngredients;

}
