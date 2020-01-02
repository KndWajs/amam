package com.aws.codestar.projecttemplates.persistence.entities;

import com.aws.codestar.projecttemplates.enums.IngredientUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements EntityInterface, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
