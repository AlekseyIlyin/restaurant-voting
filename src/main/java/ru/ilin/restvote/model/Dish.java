package ru.ilin.restvote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.ilin.restvote.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity implements HasId {

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Menu menu;

    @Column(name = "price")
    @NotBlank
    private int price;

    public Dish() {
    }

    public Dish(Integer id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Dish(Dish dish, Menu menu) {
        this.name = dish.name;
        this.price = dish.price;
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                ", id=" + id +
                ", name=" + name +
                ", price='" + price + '\'' +
                '}';
    }
}
