package com.aws.codestar.projecttemplates.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "mealIngredients")
@Getter
@Setter
@ToString
public class MealIngredient {

    @Id
    @Column(name="id")
    private Integer id;


    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "mealId")
    private Meal meal;


    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredientId")
    private Ingredient ingredient;

    @Column(name="amount")
    private Double amount;

}
