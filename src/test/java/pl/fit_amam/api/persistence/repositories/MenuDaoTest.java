package pl.fit_amam.api.persistence.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.fit_amam.api.base.AbstractIntegrationTestBase;
import pl.fit_amam.api.base.MenuGenerator;
import pl.fit_amam.api.persistence.entities.Menu;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class MenuDaoTest extends AbstractIntegrationTestBase {
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