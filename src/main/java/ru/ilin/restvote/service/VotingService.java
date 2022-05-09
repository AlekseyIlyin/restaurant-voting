package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.repository.VotingRepository;
import ru.ilin.restvote.to.VotingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service("votingService")
public class VotingService {
    private final VotingRepository repository;
    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public VotingService(VotingRepository repository, RestaurantService restaurantService, UserService userService) {
        this.repository = repository;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public List<VotingResult> getRateVotingByDate(LocalDate date) {
        return repository.getRateVotingByDate(date.atStartOfDay(), date.atTime(Vote.TIME_BEFORE_VOTING));
    }

    @Transactional
    @Modifying
    public void saveVote(Vote vote) {
        repository.save(vote);
    }

    public void save(int restaurantId, LocalDateTime dateTime, int userId) {
        saveVote(new Vote(restaurantService.getById(restaurantId), userService.get(userId), dateTime));
    }
}
