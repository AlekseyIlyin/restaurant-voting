package ru.ilin.restvote.utils;

import ru.ilin.restvote.model.Dish;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.to.DishTo;
import ru.ilin.restvote.to.MenuTo;

import java.util.stream.Collectors;

public class DishUtil {
    public static Dish createNewFromTo(DishTo dishTo) {
        return new Dish(dishTo.id(), dishTo.getName(), dishTo.getPrice());
    }

    public static DishTo asTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    public static Dish toAs(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), dishTo.getPrice());
    }


    public static Menu updateFromTo(Menu menu, MenuTo menuTo) {
        menu.setId(menuTo.getId());
        menu.setDate(menuTo.getDate());
        menu.setDishes(menuTo.getDishes().stream().map(DishUtil::toAs).collect(Collectors.toList()));
        return menu;
    }
}
