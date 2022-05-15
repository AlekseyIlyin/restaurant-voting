package ru.ilin.restvote.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.utils.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.ilin.restvote.MenuTestData.*;

@SpringBootTest
public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuService service;

    @Test
    public void create() {
        Menu created = service.create(getNew());
        int newId = created.getId();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(service.get(newId), newMenu);
    }

    @Test
    void duplicateRestaurantAndDateCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Menu(null, restaurantForMenu1, LocalDate.now())));
    }

    @Test
    void delete() {
        Menu created = service.create(getNew());
        int newId = created.getId();
        service.delete(newId);
        assertThrows(NotFoundException.class, () -> service.get(newId));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(MENU_NOT_FOUND));
    }

    @Test
    void get() {
        Menu menu = service.get(MENU1_ID);
        MENU_MATCHER.assertMatch(menu, menu1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(MENU_NOT_FOUND));
    }

    @Test
    void update() {
        Menu updated = getUpdated();
        service.update(updated);
        MENU_MATCHER.assertMatch(service.get(MENU_ID), getUpdated());
    }

    @Test
    void getAllByDate() {
        List<Menu> all = service.getAllByDate(LocalDate.now());
        MENU_MATCHER.assertMatch(all, menu1, menu2, menu3);
    }

    @Test
    void getByRestaurantAndDate() {
        MENU_MATCHER.assertMatch(menu1, service.getByRestaurantAndDate(restaurantForMenu1.getId(), LocalDate.now()));
    }
}