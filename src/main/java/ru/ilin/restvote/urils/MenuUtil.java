package ru.ilin.restvote.urils;

import ru.ilin.restvote.model.Dish;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.to.DishTo;
import ru.ilin.restvote.to.MenuTo;

import java.util.List;
import java.util.stream.Collectors;

public class MenuUtil {
    public static Menu createNewFromTo(MenuTo menuTo) {
        Menu menu = new Menu(null, RestaurantUtil.toAs(menuTo.getRestaurant()), menuTo.getDate());
        menu.setDishes(getDishesFromDishesTo(menuTo.getDishes(), menu));
        return menu;
    }

    public static MenuTo asTo(Menu menu) {
        return new MenuTo(menu.getId(), RestaurantUtil.asTo(menu.getRestaurant()), menu.getDate(),
                menu.getDishes().stream().map(DishUtil::asTo).collect(Collectors.toList()));
    }

    public static Menu updateFromTo(Menu menu, MenuTo menuTo) {
        menu.setId(menuTo.getId());
        menu.setDate(menuTo.getDate());
        menu.setDishes(getDishesFromDishesTo(menuTo.getDishes(), menu));
        return menu;
    }

    private static List<Dish> getDishesFromDishesTo(List<DishTo> dishesTos, Menu menu) {
        return dishesTos.stream().map(DishUtil::toAs).peek(dish -> dish.setMenu(menu)).collect(Collectors.toList());
    }
}
