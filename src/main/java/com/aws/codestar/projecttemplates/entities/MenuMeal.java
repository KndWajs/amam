package com.aws.codestar.projecttemplates.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "menuMeals")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuMeal implements EntityInterface {

    @Id
    @Column(name = "id")
    private Long id;
//TODO this id in manytomany table is not necessary

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "menuId")//TODO not necessary>
    private Menu menu;

    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "mealId")
    private Meal meal;

    @Column(name = "day")
    private Integer dayNumber;

}
