package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.persistence.entities.ShoppingList;
import com.aws.codestar.projecttemplates.persistence.entities.QShoppingList;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ShoppingListDao {
    private QShoppingList qShoppingList;

    @PersistenceContext
    private EntityManager em;

    public ShoppingListDao() {
        this.qShoppingList = QShoppingList.shoppingList;
    }

    public List<ShoppingList> getShoppingListsByArchivalStatus(boolean archival) {
        return selectFromShoppingListTable().where(this.qShoppingList.archival.eq(archival)).fetch();
    }

    private JPAQuery<ShoppingList> selectFromShoppingListTable() {
        return new JPAQueryFactory(this.em).selectFrom(this.qShoppingList);
    }
}
