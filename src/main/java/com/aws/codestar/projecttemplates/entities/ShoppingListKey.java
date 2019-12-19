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
public class ShoppingListKey implements Serializable {

    @Column(name = "ingredientId")
    Integer ingredientId;

    @Column(name = "menuId")
    Integer menuId;
}
