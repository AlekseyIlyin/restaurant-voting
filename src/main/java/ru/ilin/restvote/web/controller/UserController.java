package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ilin.restvote.model.User;
import ru.ilin.restvote.service.UserService;
import ru.ilin.restvote.to.UserTo;
import ru.ilin.restvote.utils.SecurityUtil;
import ru.ilin.restvote.utils.UserUtil;

import java.util.List;

@RestController
@RequestMapping("/rest/admin/users")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(
            @PathVariable int id
    ) {
        log.info("get {}", id);
        return service.get(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(
            @RequestBody UserTo userTo
    ) {
        if (userTo.isNew()) {
            create(userTo);
        } else {
            update(userTo, userTo.id());
        }
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(
            @PathVariable int id,
            @RequestParam boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable int id
    ) {
        log.info("delete user {} with user id {}", id, SecurityUtil.authUserId());
        service.delete(id);
    }

    public void create(UserTo userTo) {
        log.info("create user {} with user id {}", userTo, SecurityUtil.authUserId());
        service.create(UserUtil.createNewFromTo(userTo));
    }

    public void update(UserTo userTo, int id) {
        log.info("update user {} with id={} with user id {}", userTo, id, SecurityUtil.authUserId());
        service.update(UserUtil.updateFromTo(new User(), userTo));
    }
}
