package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ilin.restvote.model.Vote;
import ru.ilin.restvote.service.VotingService;
import ru.ilin.restvote.utils.SecurityUtil;
import ru.ilin.restvote.utils.exception.IllegalRequestDataException;

import java.time.LocalTime;

import static ru.ilin.restvote.utils.DateTimeUtil.TIME_FORMATTER;
import static ru.ilin.restvote.web.controller.VotingController.REST_URL;

@RestController
@RequestMapping(REST_URL)
public class VotingController {
    public static final String REST_URL = "/rest/voting";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VotingService service;

    @Autowired
    public VotingController(VotingService service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(
            @RequestParam int restId
    ) {
        log.info("vote rest id {} with user {}", restId, SecurityUtil.authUserId());
        if (LocalTime.now().isAfter(Vote.TIME_BEFORE_VOTING)) {
            throw new IllegalRequestDataException("Vote can only be changed before " + Vote.TIME_BEFORE_VOTING.format(TIME_FORMATTER));
        }
        service.createVote(restId, SecurityUtil.authUserId());
    }
}