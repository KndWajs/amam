package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import com.aws.codestar.projecttemplates.persistence.entities.QMenu;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MenuDao {
    private QMenu qMenu;

    @PersistenceContext
    private EntityManager em;

    public MenuDao() {
        this.qMenu = QMenu.menu;
    }

    public List<Menu> getMenusByArchivalStatus(boolean archival) {
        return selectFromMenuTable().where(this.qMenu.archival.eq(archival)).fetch();
    }

    private JPAQuery<Menu> selectFromMenuTable() {
        return new JPAQueryFactory(this.em).selectFrom(this.qMenu);
    }
}
