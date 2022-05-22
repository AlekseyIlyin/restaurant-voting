package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ilin.restvote.model.Restaurant;
import ru.ilin.restvote.service.RestaurantService;
import ru.ilin.restvote.to.RestaurantTo;
import ru.ilin.restvote.utils.RestaurantUtil;
import ru.ilin.restvote.utils.SecurityUtil;

import javax.validation.Valid;
import java.util.List;

import static ru.ilin.restvote.web.controller.AdminRestaurantController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController {
    public static final String REST_URL = "/rest/admin/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private RestaurantService service;

    @Autowired
    public AdminRestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants with admin user {}", SecurityUtil.authUserId());
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(
            @PathVariable int id
    ) {
        log.info("get restaurant by id {} with admin user {}", id, SecurityUtil.authUserId());
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(
            @Valid @RequestBody RestaurantTo restaurantTo
    ) {
        if (restaurantTo.isNew()) {
            create(restaurantTo);
        } else {
            update(restaurantTo, restaurantTo.id());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable int id
    ) {
        log.info("delete restaurant with id {} with admin user {}", id, SecurityUtil.authUserId());
        service.delete(id);
    }

    public void create(RestaurantTo restaurantTo) {
        log.info("create restaurant {} with admin user {}", restaurantTo, SecurityUtil.authUserId());
        service.create(RestaurantUtil.createNewFromTo(restaurantTo));
    }

    public void update(RestaurantTo restaurantTo, int id) {
        log.info("update restaurant {} with id {} with admin user {}", restaurantTo, id, SecurityUtil.authUserId());
        service.update(RestaurantUtil.updateFromTo(new Restaurant(), restaurantTo));
    }
}
