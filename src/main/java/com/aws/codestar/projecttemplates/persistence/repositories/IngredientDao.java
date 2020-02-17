package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.persistence.entities.QIngredient;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class IngredientDao {
    private QIngredient qIngredient;

    @PersistenceContext
    private EntityManager em;

    public IngredientDao() {
        this.qIngredient = QIngredient.ingredient;
    }

    public List<Ingredient> getIngredientsByPartialName(String ingredientPartialName, int numberOfResults){
        return selectFromIngredientTable()
                .where(this.qIngredient.name.contains(ingredientPartialName)).limit(numberOfResults).fetch();
    }

    public Ingredient getIngredientByNameAndUnit(Ingredient incomingIngredient){
        return selectFromIngredientTable()
                .where(this.qIngredient.name.eq(incomingIngredient.getName()), this.qIngredient.ingredientUnit
                        .eq(incomingIngredient.getIngredientUnit()))
                .fetchOne();
    }

    private JPAQuery<Ingredient> selectFromIngredientTable() {
        return new JPAQueryFactory(this.em).selectFrom(this.qIngredient);
    }
}
