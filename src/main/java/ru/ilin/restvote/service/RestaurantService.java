package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ilin.restvote.model.Restaurant;
import ru.ilin.restvote.repository.RestaurantRepository;

import java.util.List;

import static ru.ilin.restvote.utils.validation.ValidationUtil.checkNotFoundWithId;

@Service("restaurantService")
public class RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "restaurant", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurant", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Cacheable({"restaurant", "id"})
    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "restaurant", allEntries = true)
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    public void save(Restaurant restaurant) {
        repository.save(restaurant);
    }
}
