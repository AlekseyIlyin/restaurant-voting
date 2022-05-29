package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.ilin.restvote.utils.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final MenuRepository repository;

    @Autowired
    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Menu get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Cacheable("menu")
    public List<Menu> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    public Menu getByRestaurantAndDate(int restId, LocalDate date) {
        return repository.getByRestaurantAndDate(restId, date);
    }

    @CacheEvict(value = "menu", allEntries = true)
    public Menu create(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu);
    }

    @CacheEvict(value = "menu", allEntries = true)
    public void update(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        repository.save(menu);
    }

    @CacheEvict(value = "menu", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public List<Menu> getAll() {
        return repository.findAll();
    }
}
