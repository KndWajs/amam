package com.aws.codestar.projecttemplates.entities;

import com.aws.codestar.projecttemplates.enums.IngredientUnit;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private IngredientUnit ingredientUnit;

    @OneToMany(mappedBy = "ingredient")
    private List<MealIngredient> mealIngredients;
}
