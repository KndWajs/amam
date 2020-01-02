package com.aws.codestar.projecttemplates.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mealIngredients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealIngredient implements EntityInterface {

    @Id
    @Column(name = "id")
    private Long id;
//TODO this id in manytomany table is not necessary

    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "mealId")//TODO not necessary>
    private Meal meal;


    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredientId")
    private Ingredient ingredient;

    @Column(name = "amount")
    private Double amount;

}
