package ru.ilin.restvote;

import ru.ilin.restvote.model.Menu;
import ru.ilin.restvote.model.Restaurant;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static ru.ilin.restvote.RestaurantTestData.*;
import static ru.ilin.restvote.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(Menu.class, "dishes");
    public static final int MENU_ID = START_SEQ + 5;
    public static final int MENU1_ID = MENU_ID;
    public static final int MENU2_ID = MENU_ID + 1;
    public static final int MENU3_ID = MENU_ID + 2;
    public static final int MENU_NOT_FOUND = MENU_ID + 4;

    public static final Restaurant restaurantForMenu1 = restaurant1;

    public static final Menu menu1 = new Menu(MENU1_ID, restaurant1, LocalDate.now());
    public static final Menu menu2 = new Menu(MENU2_ID, restaurant2, LocalDate.now());
    public static final Menu menu3 = new Menu(MENU3_ID, restaurant3, LocalDate.now());

    public static Menu getNew() {
        return new Menu(null, restaurant1, LocalDate.now().plus(1, ChronoUnit.DAYS));
    }

    public static Menu getUpdated() {
        Menu updated = new Menu(menu1);
        updated.setDate(LocalDate.now().plus(2, ChronoUnit.DAYS));
        return updated;
    }
}
