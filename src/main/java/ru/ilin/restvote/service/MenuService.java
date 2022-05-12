package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.repository.datajpa.CrudMenuRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.ilin.restvote.utils.validation.ValidationUtil.checkNotFoundWithId;

@Service("menuService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MenuService {
    private final CrudMenuRepository repository;

    @Autowired
    public MenuService(CrudMenuRepository repository) {
        this.repository = repository;
    }

    public Menu get(int id) {
        return checkNotFoundWithId(repository.getById(id), id);
    }

    public List<Menu> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    public Menu getByRestaurantAndDate(int restId, LocalDate date) {
        return repository.getByRestaurantAndDate(restId, date);
    }

    public Menu create(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu);
    }

    public void update(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        repository.save(menu);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Transactional
    @Modifying
    public void deleteByRestaurantAndDate(int restId, LocalDate date) {
        Menu menu = repository.getByRestaurantAndDate(restId, date);
        repository.deleteDishesByMenuId(menu.getId());
        repository.deleteByRestaurantAndDate(restId, date);
    }
}
