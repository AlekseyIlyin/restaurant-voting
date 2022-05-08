package ru.ilin.restvote.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//https://ask-dev.ru/info/37294/strange-jackson-exception-being-thrown-when-serializing-hibernate-object
public class Menu extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name = "rest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    // https://stackoverflow.com/questions/2302802/how-to-fix-the-hibernate-object-references-an-unsaved-transient-instance-save
    @JoinColumn(name = "menu_id")
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date, List<Dish> dishes) {
        this(id, restaurant, date);
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "restaurant=" + restaurant +
                ", date=" + date +
                ", dishes=" + dishes +
                '}';
    }
}
