package ru.ilin.restvote.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    private final CrudMenuRepository crudRepository;

    @Autowired
    public DataJpaMenuRepository(CrudMenuRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Menu save(Menu menu) {
        return crudRepository.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return crudRepository.getById(id);
    }

    @Override
    public Menu getByRestaurantAndDate(int rest_id, LocalDate date) {
        return crudRepository.getByRestaurantAndDate(rest_id, date);
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return crudRepository.getAllByDate(date);
    }
}
