package com.aws.codestar.projecttemplates;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@ToString
public class Ingredient {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Unit unit;

}
