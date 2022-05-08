package ru.ilin.restvote.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "voting")
public class Vote extends AbstractBaseEntity {

    public static final LocalTime TIME_BEFORE_VOTING = LocalTime.of(11, 0);

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "rest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "voting")
    private LocalDateTime voting;

    public Vote(Restaurant restaurant, User user, LocalDateTime dateTime) {
        this.restaurant = restaurant;
        this.user = user;
        this.voting = dateTime;
    }

    public Vote() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public User getUser() {
        return user;
    }
}
