package com.aws.codestar.projecttemplates.persistence.repositories;

import com.aws.codestar.projecttemplates.Application;
import com.aws.codestar.projecttemplates.base.MenuGenerator;
import com.aws.codestar.projecttemplates.controllers.config.H2JpaConfig;
import com.aws.codestar.projecttemplates.persistence.entities.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@Transactional
public class MenuDaoTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MenuDao menuDao;

    @Test
    public void shouldFindOneMenuWhichArchivalIsTrue() {
        // given
        boolean isArchival = true;
        Menu menu = MenuGenerator.getSampleMenuEntity();
        menu.setId(null);
        menu.setArchival(isArchival);
        entityManager.persist(menu);
        Menu secondMenu = MenuGenerator.getSampleMenuEntity();
        secondMenu.setId(null);
        secondMenu.setArchival(!isArchival);
        entityManager.persist(secondMenu);

        // when
        List<Menu> searchResult = menuDao.getMenusByArchivalStatus(isArchival);

        // then
        assertEquals(1, searchResult.size());
    }

    @Test
    public void shouldNotFindMenuWhichArchivalIsTrue() {
        // given
        boolean isArchival = false;
        Menu menu = MenuGenerator.getSampleMenuEntity();
        menu.setId(null);
        menu.setArchival(isArchival);

        // when
        List<Menu> searchResult = menuDao.getMenusByArchivalStatus(!isArchival);

        // then
        assertEquals(0, searchResult.size());
    }

}