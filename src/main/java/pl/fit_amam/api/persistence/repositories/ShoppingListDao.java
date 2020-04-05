package pl.fit_amam.api.persistence.repositories;

import pl.fit_amam.api.persistence.entities.ShoppingList;
import pl.fit_amam.api.persistence.entities.QShoppingList;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ShoppingListDao {
    private QShoppingList qShoppingList;

    private ShoppingListRepository repository;

    @PersistenceContext
    private EntityManager em;

    public ShoppingListDao(ShoppingListRepository repository) {
        this.repository = repository;
        this.qShoppingList = QShoppingList.shoppingList;
    }

    public ShoppingListRepository getRepository() {
        return repository;
    }

    public List<ShoppingList> getShoppingListsByArchivalStatus(boolean archival) {
        return selectFromShoppingListTable().where(this.qShoppingList.archival.eq(archival)).fetch();
    }

    private JPAQuery<ShoppingList> selectFromShoppingListTable() {
        return new JPAQueryFactory(this.em).selectFrom(this.qShoppingList);
    }
}
