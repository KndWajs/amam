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

    @Column(name = "name")
    private String name;

    @Column(name = "userId")
    private Integer userId;

    @OneToMany(mappedBy = "menu")
    private List<MenuMeal> menuMeals;
}
