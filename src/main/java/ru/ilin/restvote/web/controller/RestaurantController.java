package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ilin.restvote.model.Restaurant;
import ru.ilin.restvote.service.RestaurantService;
import ru.ilin.restvote.to.RestaurantTo;
import ru.ilin.restvote.urils.RestaurantUtil;
import ru.ilin.restvote.urils.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("/rest/restaurant")
public class RestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get restaurants for user {}", SecurityUtil.authUserId());
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(
            @PathVariable int id
    ) {
        log.info("get restaurant by id {}", id);
        return service.getById(id);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(
            @RequestBody RestaurantTo restaurantTo
    ) {
        if (restaurantTo.isNew()) {
            create(restaurantTo);
        } else {
            update(restaurantTo, restaurantTo.id());
        }
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable int id
    ) {
        log.info("delete restaurant with id {} with user id {}", id, SecurityUtil.authUserId());
    }

    public void create(RestaurantTo restaurantTo) {
        log.info("create restaurant {} with user id {}", restaurantTo, SecurityUtil.authUserId());
        service.save(RestaurantUtil.createNewFromTo(restaurantTo));
    }

    public void update(RestaurantTo restaurantTo, int id) {
        log.info("update restaurant {} with id={} with user id {}", restaurantTo, id, SecurityUtil.authUserId());
        service.save(RestaurantUtil.updateFromTo(new Restaurant(), restaurantTo));
    }
}
