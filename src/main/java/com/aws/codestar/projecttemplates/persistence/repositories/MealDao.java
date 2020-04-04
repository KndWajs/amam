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
    private QMeal qMeal;

    private MealRepository repository;

    @PersistenceContext
    private EntityManager em;

    public MealDao(MealRepository repository) {
        this.repository = repository;
        this.qMeal = QMeal.meal;
    }

    public MealRepository getRepository() {
        return repository;
    }

    public List<Meal> getMealsByType(MealType mealType) {
        return selectFromMealTable().where(this.qMeal.typeOfMeal.eq(mealType)).fetch();
    }

    public List<Meal> getMealsByTypeWithoutCertainMeals(MealType mealType, List<Meal> excludedMeals) {
        Predicate predicate = qMeal.typeOfMeal.eq(mealType);

        for (Meal excludedMeal : excludedMeals) {
            predicate = this.qMeal.id.ne(excludedMeal.getId()).and(predicate);
        }

        return selectFromMealTable().where(predicate).fetch();
    }

    public List<Meal> getMealsByPartialName(String mealPartialName, int numberOfResults) {
        return selectFromMealTable().where(this.qMeal.name.contains(mealPartialName))
                .limit(numberOfResults).fetch();
    }

    private JPAQuery<Meal> selectFromMealTable() {
        return new JPAQueryFactory(this.em).selectFrom(this.qMeal);
    }
}
