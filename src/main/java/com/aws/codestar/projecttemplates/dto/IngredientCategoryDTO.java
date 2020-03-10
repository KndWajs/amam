package com.aws.codestar.projecttemplates.dto;

import com.aws.codestar.projecttemplates.persistence.entities.EntityInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategoryDTO implements EntityInterface {
    private Long id;

    private String category;
}
