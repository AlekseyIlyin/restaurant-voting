package ru.ilin.restvote.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "restaurants")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //https://ask-dev.ru/info/37294/strange-jackson-exception-being-thrown-when-serializing-hibernate-object
public class Restaurant extends AbstractNamedEntity {
    public Restaurant() {
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menu;

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
