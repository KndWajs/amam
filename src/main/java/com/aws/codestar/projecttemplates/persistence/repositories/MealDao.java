package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.enums.MealType;
import com.aws.codestar.projecttemplates.persistence.entities.Meal;
import com.aws.codestar.projecttemplates.persistence.entities.QMeal;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
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

    public List<Meal> getMealsByTypeWithoutCertainMeals(MealType mealType, List<Meal> excludedMeals) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMeal meal = QMeal.meal;

        Predicate predicate = meal.typeOfMeal.eq(mealType);

        for (Meal excludedMeal :excludedMeals) {
            predicate = meal.id.ne(excludedMeal.getId()).and(predicate);
        }

        JPAQuery<Meal> jPAQueryMeals = queryFactory.selectFrom(meal)
                .where(predicate);

        return jPAQueryMeals.fetch();
    }

    public List<Meal> getMealsByPartialName(String mealPartialName, int numberOfResults){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMeal meal = QMeal.meal;

        List<Meal> meals = queryFactory.selectFrom(meal)
                .where(meal.name.contains(mealPartialName)).limit(numberOfResults).fetch();
        return meals;
    }

}
