package ru.ilin.restvote;

import ru.ilin.restvote.model.Restaurant;

import static ru.ilin.restvote.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final int RESTAURANT_ID = START_SEQ + 2;
    public static final int RESTAURANT1_ID = RESTAURANT_ID;
    public static final int RESTAURANT2_ID = RESTAURANT_ID + 1;
    public static final int RESTAURANT3_ID = RESTAURANT_ID + 2;
    public static final int RESTAURANT_ID_NOT_FOUND = START_SEQ + 10;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Astoria");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Kosmos");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT3_ID, "Russian food (Русская кухня)");

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setName("UpdatedName");
        return updated;
    }
}
