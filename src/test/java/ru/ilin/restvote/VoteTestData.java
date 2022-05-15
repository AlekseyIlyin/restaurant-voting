package ru.ilin.restvote;

import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.to.VotingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.ilin.restvote.RestaurantTestData.restaurant1;
import static ru.ilin.restvote.UserTestData.admin;
import static ru.ilin.restvote.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(Vote.class, "voteDateTime");

    public static final MatcherFactory.Matcher<VotingResult> VOTING_RESULT_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(VotingResult.class, "");


    public static final int VOTE_1_ID = START_SEQ + 17;
    public static final int VOTE_NOT_FOUND = START_SEQ + 100;
    public static final Vote vote1 = new Vote(VOTE_1_ID, restaurant1, admin, LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)));

    public static Vote getNewVote() {
        return new Vote(restaurant1, admin, LocalDateTime.now());
    }

    public static List<VotingResult> getVotingResult() {
        return new ArrayList<>(Arrays.asList(
                new VotingResult(100003,1),
                new VotingResult(100004,1))
        );
    }
}
