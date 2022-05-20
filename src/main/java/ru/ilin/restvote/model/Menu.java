package ru.ilin.restvote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//https://ask-dev.ru/info/37294/strange-jackson-exception-being-thrown-when-serializing-hibernate-object
public class Menu extends AbstractBaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "rest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "on_date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate date;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(Menu menu) {
        this.id = menu.id;
        this.restaurant = menu.restaurant;
        this.date = menu.date;
        this.dishes = menu.dishes.stream().map(dish -> new Dish(dish, this)).collect(Collectors.toList());
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.dishes = new ArrayList<>();
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date, Collection<Dish> dishes) {
        this(id, restaurant, date);
        this.dishes = new ArrayList<>(dishes);
        this.dishes.stream().forEach(dish -> dish.setMenu(this));
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes.stream().sorted((o1, o2) -> {
            assert o1.getId() != null;
            assert o2.getId() != null;
            return o1.getId().compareTo(o2.getId());
        }).collect(Collectors.toList());
    }

    public void setDishes(Collection<Dish> dishes) {
        this.dishes = new ArrayList<>(dishes);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurant=" + restaurant.name +
                ", date=" + date;
    }
}
