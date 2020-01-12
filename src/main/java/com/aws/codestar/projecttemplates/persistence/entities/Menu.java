package com.aws.codestar.projecttemplates.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menus")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numberOfPeople")
    private int numberOfPeople;

    @Column(name = "name")
    private String name;

    @Column(name = "userId")
    private Integer userId;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.DETACH},
            mappedBy = "menu", orphanRemoval = true)
    private List<MenuMeal> menuMeals;
}
