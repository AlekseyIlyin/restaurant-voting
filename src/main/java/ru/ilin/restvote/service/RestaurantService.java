package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ilin.restvote.model.Restaurant;
import ru.ilin.restvote.repository.RestaurantRepository;

import java.util.List;

import static ru.ilin.restvote.utils.validation.ValidationUtil.checkNotFoundWithId;

@Service("restaurantService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    public void save(Restaurant restaurant) {
        repository.save(restaurant);
    }
}
