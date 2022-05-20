package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.service.VotingService;
import ru.ilin.restvote.utils.SecurityUtil;
import ru.ilin.restvote.utils.exception.IllegalRequestDataException;

import java.time.LocalTime;

import static ru.ilin.restvote.utils.DateTimeUtil.DATE_TIME_FORMATTER;
import static ru.ilin.restvote.web.ExceptionInfoHandler.EXCEPTION_VOTE;

@RestController
@RequestMapping("rest/voting")
public class VotingController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final VotingService service;

    @Autowired
    public VotingController(VotingService service) {
        this.service = service;
    }

    @PostMapping()
    public void vote(
            @RequestParam int restId
    ) {
        log.info("vote rest id {} with user {}", restId, SecurityUtil.authUserId());
        if (LocalTime.now().isAfter(Vote.TIME_BEFORE_VOTING)) {
            throw new IllegalRequestDataException(EXCEPTION_VOTE + " " + Vote.TIME_BEFORE_VOTING.format(DATE_TIME_FORMATTER));
        }
        service.createVote(restId, SecurityUtil.authUserId());
    }
}