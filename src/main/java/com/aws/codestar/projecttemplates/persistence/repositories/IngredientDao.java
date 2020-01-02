package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.persistence.entities.Ingredient;
import com.aws.codestar.projecttemplates.entities.QIngredient;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class IngredientDao {

    @PersistenceContext
    private EntityManager em;

    public List<Ingredient> getIngredientsByPartialName(String ingredientPartialName, int numberOfResults){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QIngredient ingredient = QIngredient.ingredient;

        List<Ingredient> ingredients = queryFactory.selectFrom(ingredient)
                .where(ingredient.name.contains(ingredientPartialName)).limit(numberOfResults).fetch();
        return ingredients;
    }
}
