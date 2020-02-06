package com.aws.codestar.projecttemplates.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "shoppingElements")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingElement implements EntityInterface {//TODO change to ShoppingListElement?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shoppingListId")
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "ingredientId")
    private Ingredient ingredient;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "alreadyBought")
    private boolean alreadyBought;
}
