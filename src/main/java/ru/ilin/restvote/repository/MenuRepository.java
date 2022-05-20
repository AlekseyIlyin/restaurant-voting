package ru.ilin.restvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu u WHERE u.restaurant.id=:restId AND u.date=:date")
    void deleteByRestaurantAndDate(@Param("restId") int restId, @Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish u WHERE u.menu.id=:menuId")
    void deleteDishesByMenuId(@Param("menuId") int menuId);

    @Query("SELECT u FROM Menu u WHERE u.restaurant.id=:restId AND u.date=:date")
    Menu getByRestaurantAndDate(@Param("restId") int restId, @Param("date") LocalDate date);

    @Query("SELECT u FROM Menu u WHERE u.date=:date")
    List<Menu> getAllByDate(@Param("date") LocalDate date);
}
