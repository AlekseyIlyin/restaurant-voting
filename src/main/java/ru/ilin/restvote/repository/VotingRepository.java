package ru.ilin.restvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.to.VotingResult;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface VotingRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT new ru.ilin.restvote.to.VotingResult(u.restaurant.id, COUNT(u.restaurant.id)) FROM Vote u WHERE u.voteDateTime>=:startDateTime AND u.voteDateTime<:endDateTime GROUP BY u.restaurant.id")
    List<VotingResult> getRateVotingByDate(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
}
