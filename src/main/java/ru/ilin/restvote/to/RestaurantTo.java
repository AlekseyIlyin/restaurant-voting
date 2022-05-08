package ru.ilin.restvote.to;

import javax.validation.constraints.NotBlank;

public class RestaurantTo extends BaseTo{
    @NotBlank
    private String name;

    public RestaurantTo() {
    }

    public RestaurantTo(String name) {
        super(null);
        this.name = name;
    }

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
