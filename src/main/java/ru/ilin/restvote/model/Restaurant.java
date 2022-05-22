package ru.ilin.restvote.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.ilin.restvote.HasId;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//https://ask-dev.ru/info/37294/strange-jackson-exception-being-thrown-when-serializing-hibernate-object
public class Restaurant extends AbstractNamedEntity implements HasId {

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
    }

    public Restaurant(String name) {
        super(null, name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
