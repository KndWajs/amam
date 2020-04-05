package com.aws.codestar.projecttemplates.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "menus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends AbstractBaseEntity implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numberOfPeople")
    private double numberOfPeople;

    @Column(name = "name")
    private String name;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "archival")
    private boolean archival;

    @OneToMany(cascade = {CascadeType.ALL},
            mappedBy = "menu", orphanRemoval = true)
    private List<MenuMeal> menuMeals;

    @Builder
    public Menu(String userName, Timestamp creationDate, Timestamp updateDate, Long id,
                double numberOfPeople, String name, Integer userId, boolean archival,
                List<MenuMeal> menuMeals) {
        super(userName, creationDate, updateDate);
        this.id = id;
        this.numberOfPeople = numberOfPeople;
        this.name = name;
        this.userId = userId;
        this.archival = archival;
        this.menuMeals = menuMeals;
    }
}
