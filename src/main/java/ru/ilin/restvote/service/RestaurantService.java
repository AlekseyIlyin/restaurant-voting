package ru.ilin.restvote.service;

import org.springframework.stereotype.Service;
import ru.ilin.restvote.repository.RestaurantRepository;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }
}
