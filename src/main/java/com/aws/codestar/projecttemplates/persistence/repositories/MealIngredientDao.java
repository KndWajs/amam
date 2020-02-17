package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.persistence.entities.MealIngredient;
import com.aws.codestar.projecttemplates.persistence.entities.QMealIngredient;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MealIngredientDao {
    private QMealIngredient qMealIngredient;

    @PersistenceContext
    private EntityManager em;

    public MealIngredientDao() {
        this.qMealIngredient = QMealIngredient.mealIngredient;
    }

    public List<MealIngredient> getMealIngredientsByIngredientId(Long ingredientId){
        return  selectFromMealIngredientTable()
                .where(this.qMealIngredient.ingredient.id.eq(ingredientId)).fetch();
    }

    public List<MealIngredient> getMealIngredientsByMealId(Long mealId){
        return selectFromMealIngredientTable()
                .where(this.qMealIngredient.meal.id.eq(mealId)).fetch();
    }

    private JPAQuery<MealIngredient> selectFromMealIngredientTable() {
        return new JPAQueryFactory(this.em).selectFrom(this.qMealIngredient);
    }
}
