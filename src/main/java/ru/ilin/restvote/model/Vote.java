package ru.ilin.restvote.model;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SqlResultSetMapping(
        name = "VotingResult",
        classes = {
                @ConstructorResult(
                        targetClass = ru.ilin.restvote.to.VotingResult.class,
                        columns = {
                                @ColumnResult(name = "rest_id", type = Integer.class),
                                @ColumnResult(name = "rate", type = Long.class)}
                )
        })

/*
//POSTGRES:
CREATE FUNCTION getVotingResult(startDateTime TIMESTAMP, beforeDateTime TIMESTAMP)
        RETURNS TABLE (rest_id INTEGER, rate INTEGER)
        LANGUAGE sql
        AS '
        SELECT rest_id, COUNT(rest_id) AS rate FROM voting WHERE vote_datetime IN (
        SELECT vdt.vd FROM (SELECT user_id, MAX(vote_datetime) AS vd FROM voting WHERE vote_datetime>=startDateTime AND vote_datetime < beforeDateTime GROUP BY user_id) AS vdt) GROUP BY rest_id;
        ';
//HSQL:
CREATE FUNCTION getVotingResult(startDateTime TIMESTAMP, beforeDateTime TIMESTAMP)
    RETURNS TABLE (rest_id INTEGER, rate INTEGER)
    READS SQL DATA
        RETURN TABLE (
        SELECT rest_id, COUNT(rest_id) AS rate FROM voting WHERE vote_datetime IN (
            SELECT vdt.vd FROM (SELECT user_id, MAX(vote_datetime) AS vd FROM voting WHERE vote_datetime>=startDateTime AND vote_datetime < beforeDateTime GROUP BY user_id) AS vdt) GROUP BY rest_id);
*/
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Vote.GET_RESULT_VOTING,
                query = "SELECT * FROM TABLE(getvotingresult(?,?))",
                resultSetMapping = "VotingResult"
        )
})

@Entity
@Table(name = "voting")
public class Vote extends AbstractBaseEntity {

    public static final String GET_RESULT_VOTING = "Vote.getResultVoting";
    public static final LocalTime TIME_BEFORE_VOTING = LocalTime.of(11, 0);

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "rest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "vote_datetime", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
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
