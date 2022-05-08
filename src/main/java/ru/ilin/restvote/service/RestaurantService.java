package ru.ilin.restvote.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.Restaurant;
import ru.ilin.restvote.repository.RestaurantRepository;

import java.util.List;

@Service("restaurantService")
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant getById(int id) {
        return repository.getById(id);
    }

    @Transactional
    public void save(Restaurant restaurant) {
        repository.save(restaurant);
    }

    @Transactional
    public void delete(int id) {
        repository.delete(getById(id));
    }
}
