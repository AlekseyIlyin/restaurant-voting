package ru.ilin.restvote.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import ru.ilin.restvote.model.Restaurant;
import ru.ilin.restvote.utils.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.ilin.restvote.RestaurantTestData.*;

@SpringBootTest
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    public void create() {
        Restaurant created = service.create(getNew());
        int newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Restaurant(null, "Astoria")));
    }

    @Test
    void delete() {
        Restaurant created = service.create(getNew());
        int newId = created.getId();
        service.delete(newId);
        assertThrows(NotFoundException.class, () -> service.get(newId));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(RESTAURANT_ID_NOT_FOUND));
    }

    @Test
    void get() {
        Restaurant restaurant = service.get(RESTAURANT_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_ID_NOT_FOUND));
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT_ID), getUpdated());
    }

    @Test
    void getAll() {
        List<Restaurant> all = service.getAll();
        RESTAURANT_MATCHER.assertMatch(all, restaurant1, restaurant2, restaurant3);
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Restaurant(null, "  ")));
    }
}