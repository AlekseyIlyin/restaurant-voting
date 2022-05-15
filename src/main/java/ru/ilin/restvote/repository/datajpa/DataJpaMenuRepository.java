package ru.ilin.restvote.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    private final CrudMenuRepository menuRepository;

    @Autowired
    public DataJpaMenuRepository(CrudMenuRepository crudRepository) {
        this.menuRepository = crudRepository;
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return menuRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public Menu getByRestaurantAndDate(int rest_id, LocalDate date) {
        return menuRepository.getByRestaurantAndDate(rest_id, date);
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return menuRepository.getAllByDate(date);
    }
}
