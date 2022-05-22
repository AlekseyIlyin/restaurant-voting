package ru.ilin.restvote.to;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuTo extends BaseTo {

    @NotNull
    private RestaurantTo restaurantTo;

    @NotNull
    private LocalDate date;

    List<DishTo> dishesTo;

    public MenuTo() {
    }

    public MenuTo(Integer id, RestaurantTo restaurantTo, LocalDate date, List<DishTo> dishesTo) {
        super(id);
        this.restaurantTo = restaurantTo;
        this.date = date;
        this.dishesTo = new ArrayList<>(dishesTo);
    }

    public RestaurantTo getRestaurant() {
        return restaurantTo;
    }

    public void setRestaurant(RestaurantTo restaurant) {
        this.restaurantTo = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<DishTo> getDishes() {
        return dishesTo;
    }

    public void setDishes(List<DishTo> dishesTo) {
        this.dishesTo = new ArrayList<>(dishesTo);
    }
}
