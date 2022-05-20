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

import static ru.ilin.restvote.utils.DateTimeUtil.getNowIfNullDate;

@RestController
@RequestMapping("/rest")
public class MenuController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MenuService service;

    @Autowired
    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping("/menus")
    public List<MenuTo> getAll() {
        log.info("get all menus with user id {}", SecurityUtil.authUserId());
        return service.getAll().stream().map(MenuUtil::asTo).collect(Collectors.toList());
    }

    @GetMapping("/menus/bydate")
    public List<MenuTo> getAllByDate(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.info("get all menu for date {} with user id {}", getNowIfNullDate(date), SecurityUtil.authUserId());
        return service.getAllByDate(date == null ? LocalDate.now() : date).stream().map(MenuUtil::asTo).collect(Collectors.toList());
    }

    @GetMapping("/menus/{restId}")
    public MenuTo getByRestaurantAndDate(
            @PathVariable int restId,
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.info("get menu for restaurant id {} and date {} with user id {}", restId, date, SecurityUtil.authUserId());
        return MenuUtil.asTo(service.getByRestaurantAndDate(restId, getNowIfNullDate(date)));
    }

    @PostMapping("/admin/menus")
    public void save(
            @RequestBody MenuTo menuTo
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
