package com.aws.codestar.projecttemplates.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "shoppingList")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingList {//TODO change to ShoppingListElement?

    @EmbeddedId
    ShoppingListKey id;

    @ManyToOne
    @JoinColumn(name = "ingredientId", updatable = false, insertable = false)
    private Ingredient ingredient;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "userId")
    private Integer userId;
}
