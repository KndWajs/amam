package com.aws.codestar.projecttemplates.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@Getter
@Setter
@ToString
public class Menu {

    @Id
    @Column(name="dayNumber")
    private Integer dayNumber;

    @Column(name="mealName")
    private String mealName;
}
