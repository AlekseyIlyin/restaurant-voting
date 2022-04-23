package ru.ilin.restvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.Voting;

@Transactional(readOnly = true)
public interface VotingRepository extends JpaRepository<Voting, Integer> {
}
