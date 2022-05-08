package ru.ilin.restvote.to;

public class DishTo extends BaseTo{

    private String name;
    private float price;

    public DishTo() {
    }

    public DishTo(Integer id, String name, float price) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
