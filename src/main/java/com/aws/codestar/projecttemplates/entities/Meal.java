package com.aws.codestar.projecttemplates.entities;

import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.enums.PreparingType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meals")
@Getter
@Setter
@ToString
public class Meal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="typeOfMeal")
    private MealType typeOfMeal;

    @Enumerated(EnumType.STRING)
    @Column(name="typeOfPreparing")
    private PreparingType typeOfPreparing;

    @Column(name="recipe")
    private String recipe;

    @Column(name="minToPrepare")
    private Integer minutesToPrepare;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "mealIngredients",
//            joinColumns = @JoinColumn(name = "mealId"),
//            inverseJoinColumns = @JoinColumn(name = "ingredientId"))

    @OneToMany (mappedBy = "meal")
    private List<MealIngredient> mealIngredients;
}
