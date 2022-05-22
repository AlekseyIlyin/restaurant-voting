package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilin.restvote.model.Restaurant;
import ru.ilin.restvote.service.RestaurantService;
import ru.ilin.restvote.utils.SecurityUtil;

import java.util.List;

import static ru.ilin.restvote.web.controller.RestaurantController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    public static final String REST_URL = "/rest/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants with user {}", SecurityUtil.authUserId());
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(
            @PathVariable int id
    ) {
        log.info("get restaurant by id {} with user {}", id, SecurityUtil.authUserId());
        return service.get(id);
    }
}
