package com.aws.codestar.projecttemplates.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MealIngredientKey implements Serializable {

    @Column(name = "mealId")
    Integer mealId;

    @Column(name = "ingredientId")
    Integer ingredientId;

}
