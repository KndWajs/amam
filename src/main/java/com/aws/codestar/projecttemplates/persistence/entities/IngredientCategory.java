package com.aws.codestar.projecttemplates.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ingredientCategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategory extends AbstractBaseEntity implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category")
    private String category;

    @Builder
    public IngredientCategory(String userName, Timestamp creationDate, Timestamp updateDate,
                              Long id, String category) {
        super(userName, creationDate, updateDate);
        this.id = id;
        this.category = category;
    }
}
