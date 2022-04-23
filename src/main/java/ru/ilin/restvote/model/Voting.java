package ru.ilin.restvote.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voting")
public class Voting extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_ID")
    private Restaurant restaurant;

    @Column(name = "voting")
    private LocalDateTime voting;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public User getUser() {
        return user;
    }
}
