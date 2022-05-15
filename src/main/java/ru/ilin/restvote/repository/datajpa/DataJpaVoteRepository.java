package ru.ilin.restvote.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.repository.VoteRepository;
import ru.ilin.restvote.to.VotingResult;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudRepository;

    @Autowired
    public DataJpaVoteRepository(CrudVoteRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Vote save(Vote vote) {
        return crudRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<VotingResult> getResultVoting(LocalDateTime startDateTime, LocalDateTime beforeDateTime) {
        return crudRepository.getResultVoting(startDateTime, beforeDateTime);
    }
}
