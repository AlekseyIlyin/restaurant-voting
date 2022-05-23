package ru.ilin.restvote.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.utils.exception.IllegalRequestDataException;
import ru.ilin.restvote.utils.exception.NotFoundException;

import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.ilin.restvote.RestaurantTestData.RESTAURANT1_ID;
import static ru.ilin.restvote.UserTestData.ADMIN_ID;
import static ru.ilin.restvote.VoteTestData.*;

@SpringBootTest
public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VotingService service;

    @Test
    public void createVote() {
        Vote created = service.createVote(RESTAURANT1_ID, ADMIN_ID, VOTE_LAST_DATETIME_FOR_VOTING);
        int newId = created.getId();
        Vote newVote = getNewVote();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.get(newId), newVote);
    }

    @Test
    public void createForbiddenVote() {
        assertThrows(IllegalRequestDataException.class,
                () -> service.createVote(RESTAURANT1_ID, ADMIN_ID, VOTE_LAST_DATETIME_FOR_VOTING.plus(1, ChronoUnit.SECONDS)));
    }

    @Test
    void get() {
        Vote vote = service.get(VOTE_1_ID);
        VOTE_MATCHER.assertMatch(vote, vote1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(VOTE_NOT_FOUND));
    }

    @Test
    void delete() {
        Vote created = service.createVote(RESTAURANT1_ID, ADMIN_ID, VOTE_LAST_DATETIME_FOR_VOTING);
        int newId = created.getId();
        service.delete(newId);
        assertThrows(NotFoundException.class, () -> service.get(newId));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(VOTE_NOT_FOUND));
    }
}