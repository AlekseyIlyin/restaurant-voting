package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.repository.VoteRepository;
import ru.ilin.restvote.to.VotingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.ilin.restvote.utils.validation.ValidationUtil.checkNotFoundWithId;

@Service("votingService")
public class VotingService {
    private final VoteRepository repository;
    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public VotingService(VoteRepository repository, RestaurantService restaurantService, UserService userService) {
        this.repository = repository;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<VotingResult> getRateVotingByDate(LocalDate date) {
        return repository.getResultVoting(date.atStartOfDay(), date.atTime(Vote.TIME_BEFORE_VOTING));
    }

    @Transactional
    @Modifying
    public Vote createVote(int restaurantId, LocalDateTime dateTime, int userId) {
        return repository.save(new Vote(restaurantService.get(restaurantId), userService.get(userId), dateTime));
    }
}
