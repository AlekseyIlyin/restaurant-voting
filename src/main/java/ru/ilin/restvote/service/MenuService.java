package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Service("menuService")
public class MenuService {
    private final MenuRepository repository;
    private final RestaurantService restaurantService;

    @Autowired
    public MenuService(MenuRepository repository, RestaurantService restaurantService) {
        this.repository = repository;
        this.restaurantService = restaurantService;
    }

    public List<Menu> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    public Menu getByRestaurantAndDate(int restId, LocalDate date) {
        return repository.getByRestaurantAndDate(restId, date);
    }

    @Transactional
    @Modifying
    public void save(Menu menu) {
        repository.save(menu);
    }

    @Transactional
    @Modifying
    public void deleteByRestaurantAndDate(int restId, LocalDate date) {
        Menu menu = repository.getByRestaurantAndDate(restId, date);
        repository.deleteDishesByMenuId(menu.getId());
        repository.deleteByRestaurantAndDate(restId, date);
    }
}
