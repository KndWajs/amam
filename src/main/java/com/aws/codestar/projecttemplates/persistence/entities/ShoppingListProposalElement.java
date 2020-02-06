package com.aws.codestar.projecttemplates.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "shoppingListProposalElements")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListProposalElement {

    @EmbeddedId
    private ShoppingListProposalElementKey id;

    @ManyToOne
    @JoinColumn(name = "ingredientId", updatable = false, insertable = false)
    private Ingredient ingredient;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "userId")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "menuId", referencedColumnName = "id", updatable = false, insertable = false)
    private Menu menu;
}
