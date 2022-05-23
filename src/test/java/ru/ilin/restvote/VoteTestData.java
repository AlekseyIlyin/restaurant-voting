package ru.ilin.restvote;

import ru.ilin.restvote.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static ru.ilin.restvote.RestaurantTestData.restaurant1;
import static ru.ilin.restvote.UserTestData.admin;
import static ru.ilin.restvote.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(Vote.class, "user.password", "user.registered");

    public static final LocalDateTime VOTE_LAST_DATETIME_FOR_VOTING = LocalDateTime.of(LocalDate.now(), Vote.VOTE_LAST_TIME_FOR_VOTING);
    public static final int VOTE_1_ID = START_SEQ + 17;
    public static final int VOTE_NOT_FOUND = START_SEQ + 100;
    public static final Vote vote1 = new Vote(VOTE_1_ID, restaurant1, admin);

    public static Vote getNewVote() {
        return new Vote(restaurant1, admin);
    }
}
