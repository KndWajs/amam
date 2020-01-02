package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.persistence.entities.MealIngredient;
import com.aws.codestar.projecttemplates.entities.QMealIngredient;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MealIngredientDao {

    @PersistenceContext
    private EntityManager em;

    public List<MealIngredient> getMealIngredientsByIngredientId(Long ingredientId){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMealIngredient mealIngredient = QMealIngredient.mealIngredient;

        List<MealIngredient> mealIngredients = queryFactory.selectFrom(mealIngredient)
                .where(mealIngredient.ingredient.id.eq(ingredientId)).fetch();
        return mealIngredients;
    }

    //todo refactor those methods

    public List<MealIngredient> getMealIngredientsByMealId(Long mealId){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMealIngredient mealIngredient = QMealIngredient.mealIngredient;

        List<MealIngredient> mealIngredients = queryFactory.selectFrom(mealIngredient)
                .where(mealIngredient.meal.id.eq(mealId)).fetch();
        return mealIngredients;
    }


}
