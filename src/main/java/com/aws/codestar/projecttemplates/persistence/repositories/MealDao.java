package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.entities.QMeal;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MealDao {

    @PersistenceContext
    private EntityManager em;

    public List<Meal> getMealsByMealType(MealType mealType){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMeal meal = QMeal.meal;

        List<Meal> meals = queryFactory.selectFrom(meal)
                .where(meal.typeOfMeal.eq(mealType)).fetch();
        return meals;
    }

    public List<Meal> getMealsByPartialName(String mealPartialName, int numberOfResults){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMeal meal = QMeal.meal;

        List<Meal> meals = queryFactory.selectFrom(meal)
                .where(meal.name.contains(mealPartialName)).limit(numberOfResults).fetch();
        return meals;
    }
}
