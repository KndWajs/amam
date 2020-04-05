package com.aws.codestar.projecttemplates.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "shoppingElements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingElement extends AbstractBaseEntity implements EntityInterface {

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

    @Column(name = "alreadyBought")
    private boolean alreadyBought;

    @Builder
    public ShoppingElement(String userName, Timestamp creationDate, Timestamp updateDate,
                           Long id, ShoppingList shoppingList,
                           Ingredient ingredient, Double amount, boolean alreadyBought) {
        super(userName, creationDate, updateDate);
        this.id = id;
        this.shoppingList = shoppingList;
        this.ingredient = ingredient;
        this.amount = amount;
        this.alreadyBought = alreadyBought;
    }
}
