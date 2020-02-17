package com.aws.codestar.projecttemplates.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "shoppingLists")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingList implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "numberOfPeople")
    private double numberOfPeople;

    @Column(name = "archival")
    private boolean archival;

    @OneToMany(
            cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.DETACH}
            ,mappedBy = "shoppingList"
            ,orphanRemoval = true
    )
    private List<ShoppingElement> shoppingElements;
}
