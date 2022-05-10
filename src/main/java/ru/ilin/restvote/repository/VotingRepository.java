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
    @Query(nativeQuery = true) // https://stackoverflow.com/questions/49056084/got-different-size-of-tuples-and-aliases-exception-after-spring-boot-2-0-0-rel
    List<VotingResult> getResultVoting(@Param("startDateTime") LocalDateTime startDateTime, @Param("beforeDateTime") LocalDateTime beforeDateTime);
}
