package ru.ilin.restvote.repository;

import ru.ilin.restvote.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant user);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();
}
