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

    @Column(name = "vote_datetime")
    private LocalDateTime voteDateTime;

    public Vote(Restaurant restaurant, User user, LocalDateTime voteDateTime) {
        this.restaurant = restaurant;
        this.user = user;
        this.voteDateTime = voteDateTime;
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
