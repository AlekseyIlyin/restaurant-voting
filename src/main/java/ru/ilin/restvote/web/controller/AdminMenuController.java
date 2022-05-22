package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.service.MenuService;
import ru.ilin.restvote.to.MenuTo;
import ru.ilin.restvote.utils.MenuUtil;
import ru.ilin.restvote.utils.SecurityUtil;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.ilin.restvote.utils.DateTimeUtil.getNowIfNullDate;
import static ru.ilin.restvote.web.controller.AdminMenuController.REST_URL;

@RestController
@RequestMapping(REST_URL)
public class AdminMenuController {
    public static final String REST_URL = "/rest/admin/menus";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuService service;

    @Autowired
    public AdminMenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    public List<Menu> getAll() {
        log.info("get all menus with admin user id {}", SecurityUtil.authUserId());
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Menu get(
            @PathVariable int id
    ) {
        log.info("get menu by id {} with admin user {}", id, SecurityUtil.authUserId());
        return service.get(id);
    }

    @GetMapping("/bydate")
    public List<MenuTo> getAllByDate(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.info("get all menu for date {} with admin user id {}", getNowIfNullDate(date), SecurityUtil.authUserId());
        return service.getAllByDate(date == null ? LocalDate.now() : date).stream().map(MenuUtil::asTo).collect(Collectors.toList());
    }

    @GetMapping("/restaurant/{restId}")
    public MenuTo getByRestaurantAndDate(
            @PathVariable int restId,
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.info("get menu for restaurant id {} and date {} with admin user id {}", restId, date, SecurityUtil.authUserId());
        return MenuUtil.asTo(service.getByRestaurantAndDate(restId, getNowIfNullDate(date)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable int id
    ) {
        log.info("delete menu with id {} with admin user {}", id, SecurityUtil.authUserId());
        service.delete(id);
    }

    @PostMapping
    public void save(
            @Valid @RequestBody MenuTo menuTo
    ) {
        if (menuTo.isNew()) {
            create(menuTo);
        } else {
            update(menuTo, menuTo.id());
        }
    }

    public void create(MenuTo menuTo) {
        log.info("create menu {} with user id {}", menuTo, SecurityUtil.authUserId());
        service.create(MenuUtil.createNewFromTo(menuTo));
    }

    public void update(MenuTo menuTo, int id) {
        log.info("update menu {} with id={} with user id {}", menuTo, id, SecurityUtil.authUserId());
        service.update(MenuUtil.updateFromTo(new Menu(), menuTo));
    }
}
