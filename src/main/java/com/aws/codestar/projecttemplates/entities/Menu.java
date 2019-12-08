package com.aws.codestar.projecttemplates.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements EntityInterface {

    @Id
    @Column(name = "dayNumber")
    private Long id;  //TODO id which is also day numer can be misleading

    @Column(name = "mealName")
    private String mealName;
}
