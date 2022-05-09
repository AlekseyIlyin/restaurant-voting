package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.service.MenuService;
import ru.ilin.restvote.to.MenuTo;
import ru.ilin.restvote.utils.MenuUtil;
import ru.ilin.restvote.utils.SecurityUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/menu")
public class MenuController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MenuService service;

    @Autowired
    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping()
    public List<MenuTo> getAllByDate(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.info("get all menu for date {} with user id {}", date == null ? LocalDate.now() : date, SecurityUtil.authUserId());
        return service.getAllByDate(date == null ? LocalDate.now() : date).stream().map(MenuUtil::asTo).collect(Collectors.toList());
    }

    @GetMapping("/{rest_id}")
    public MenuTo getByRestaurantAndDate(
            @PathVariable int rest_id,
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.info("get menu for restaurant id {} and date {} with user id {}", rest_id, date, SecurityUtil.authUserId());
        return MenuUtil.asTo(service.getByRestaurantAndDate(rest_id, date == null ? LocalDate.now() : date));
    }

    @PostMapping("/admin")
    public void save(
            @RequestBody MenuTo menuTo
    ) {
        if (menuTo.isNew()) {
            create(menuTo);
        } else {
            update(menuTo, menuTo.id());
        }
    }

    @DeleteMapping("/admin/{rest_id}")
    public void deleteByRestaurantAndDate(
            @PathVariable int rest_id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.info("delete menu rest id {} and date {} with user id {}", rest_id, date, SecurityUtil.authUserId());
        service.deleteByRestaurantAndDate(rest_id, date);
    }

    public void create(MenuTo menuTo) {
        log.info("create menu {} with user id {}", menuTo, SecurityUtil.authUserId());
        service.save(MenuUtil.createNewFromTo(menuTo));
    }

    public void update(MenuTo menuTo, int id) {
        log.info("update menu {} with id={} with user id {}", menuTo, id, SecurityUtil.authUserId());
        service.save(MenuUtil.updateFromTo(new Menu(), menuTo));
    }
}
