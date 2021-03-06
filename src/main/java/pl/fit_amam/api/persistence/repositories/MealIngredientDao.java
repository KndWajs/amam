package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.MealIngredient;
import pl.fit_amam.api.persistence.entities.QMealIngredient;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MealIngredientDao {
    private QMealIngredient qMealIngredient;

    private MealIngredientRepository repository;

    @PersistenceContext
    private EntityManager em;

    public MealIngredientDao(MealIngredientRepository repository) {
        this.repository = repository;
        this.qMealIngredient = QMealIngredient.mealIngredient;
    }

    public MealIngredientRepository getRepository() {
        return repository;
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
