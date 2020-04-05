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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mealId")
    private Meal meal;


    @ManyToOne
    @JoinColumn(name = "ingredientId")
    private Ingredient ingredient;

    @Column(name = "amount")
    private Double amount;

}
