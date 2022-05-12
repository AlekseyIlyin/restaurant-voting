package ru.ilin.restvote.repository;

import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.to.VotingResult;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id);

    List<VotingResult> getResultVoting(LocalDateTime startDateTime, LocalDateTime beforeDateTime);
}
