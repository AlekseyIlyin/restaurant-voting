package ru.ilin.restvote;

import ru.ilin.restvote.model.Dish;
import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.model.Restaurant;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import static ru.ilin.restvote.RestaurantTestData.*;
import static ru.ilin.restvote.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(Menu.class, "");

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER_WITHOUT_DISHES = MatcherFactory
            .usingIgnoringFieldsComparator(Menu.class, "dishes");

    public static final int MENU_ID = START_SEQ + 5;
    public static final int MENU1_ID = MENU_ID;
    public static final int MENU2_ID = MENU_ID + 1;
    public static final int MENU3_ID = MENU_ID + 2;
    public static final int MENU_NOT_FOUND = MENU_ID + 4;

    public static final int DISH_ID = START_SEQ + 8;

    public static final Restaurant restaurantForMenu1 = restaurant1;

    public static final Menu menu1 = new Menu(MENU1_ID, restaurant1, LocalDate.now(),
            new ArrayList<>(Arrays.asList(
                    new Dish(DISH_ID, "ice cream", 3.99f),
                    new Dish(DISH_ID + 1, "Cinnamon waffle", 4.99f),
                    new Dish(DISH_ID + 2, "Fruit trifle", 3.44f)))
    );

    public static final Menu menu2 = new Menu(MENU2_ID, restaurant2, LocalDate.now(),
            new ArrayList<>(Arrays.asList(
                    new Dish(DISH_ID + 3, "Garlic Bread", 3.50f),
                    new Dish(DISH_ID + 4, "Mozzarella salad", 4.50f),
                    new Dish(DISH_ID + 5, "Olives", 3.99f)))
    );

    public static final Menu menu3 = new Menu(MENU3_ID, restaurant3, LocalDate.now(),
            new ArrayList<>(Arrays.asList(
                    new Dish(DISH_ID + 6, "Жульен", 3.59f),
                    new Dish(DISH_ID + 7, "Курник", 3.99f),
                    new Dish(DISH_ID + 8, "Растягай", 2.11f)))
    );

    public static Menu getNew() {
        return new Menu(null, restaurant1, LocalDate.now().plus(1, ChronoUnit.DAYS));
    }

    public static Menu getUpdated() {
        Menu updated = new Menu(menu1);
        updated.setDate(LocalDate.now().plus(2, ChronoUnit.DAYS));
        updated.setDishes(new ArrayList<>());
        return updated;
    }
}
