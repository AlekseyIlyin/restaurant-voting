package ru.ilin.restvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "price")
    @NotBlank
    private float price;

    public Dish() {
    }

    public Dish(Integer id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name=" + name +
                ", price='" + price + '\'' +
                '}';
    }
}
