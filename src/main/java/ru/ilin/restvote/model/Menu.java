package ru.ilin.restvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @OneToMany
    @JoinColumn(name = "menu_id")
    private Set<Dishes> dishes;

    public Menu() {
    }

    public Menu(Integer id) {
        super(id);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
