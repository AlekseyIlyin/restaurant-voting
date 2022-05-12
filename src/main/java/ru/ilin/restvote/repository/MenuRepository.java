package ru.ilin.restvote.repository;

import ru.ilin.restvote.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu);

    // false if not found
    boolean delete(int id);

    // null if not found
    Menu get(int id);

    Menu getByRestaurantAndDate(int rest_id, LocalDate date);

    List<Menu> getAllByDate(LocalDate date);
}
