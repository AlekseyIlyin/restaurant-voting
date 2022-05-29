package ru.ilin.restvote.to;

import javax.validation.constraints.NotBlank;

public class DishTo extends BaseTo {

    @NotBlank
    private String name;
    private int price;

    public DishTo() {
    }

    public DishTo(Integer id, String name, int price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
