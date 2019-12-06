package com.aws.codestar.projecttemplates.entities;

import com.aws.codestar.projecttemplates.enums.IngredientUnit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@ToString
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private IngredientUnit ingredientUnit;

    //    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "ingredients")
    @OneToMany(mappedBy = "ingredient")
    private List<MealIngredient> mealIngredients;
}
