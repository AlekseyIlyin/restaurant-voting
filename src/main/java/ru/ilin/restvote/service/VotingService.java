package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.repository.VoteRepository;
import ru.ilin.restvote.utils.exception.IllegalRequestDataException;

import java.time.LocalDateTime;

import static ru.ilin.restvote.utils.DateTimeUtil.TIME_FORMATTER;
import static ru.ilin.restvote.utils.validation.ValidationUtil.checkNotFoundWithId;

@Service
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
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Transactional
    public Vote createVote(int restaurantId, int userId, LocalDateTime dateTineVoting) {
        if (dateTineVoting.toLocalTime().isAfter(Vote.VOTE_LAST_TIME_FOR_VOTING)) {
            throw new IllegalRequestDataException("Vote can only be changed before " + Vote.VOTE_LAST_TIME_FOR_VOTING.format(TIME_FORMATTER));
        }
        repository.deleteByUserId(userId);
        return repository.save(new Vote(restaurantService.get(restaurantId), userService.get(userId)));
    }
}