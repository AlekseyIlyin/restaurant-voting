package ru.ilin.restvote.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.ilin.restvote.service.VotingService;
import ru.ilin.restvote.to.VotingResult;
import ru.ilin.restvote.urils.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("rest/vote")
public class VotingController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final VotingService service;

    @Autowired
    public VotingController(VotingService service) {
        this.service = service;
    }

    @GetMapping("/rating")
    public List<VotingResult> getRateVotingByDate(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.info("get result voting by date {} for user {}", date == null ? LocalDate.now() : date, SecurityUtil.authUserId());
        return service.getRateVotingByDate(date);
    }

    @PostMapping()
    public void vote(
            @RequestParam int rest_id,
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime
    ) {
        log.info("vote rest id {} and date {} with user {}", rest_id, dateTime == null ? LocalDate.now() : dateTime, SecurityUtil.authUserId());
        service.save(rest_id, dateTime == null ? LocalDateTime.now() : dateTime, SecurityUtil.authUserId());
    }
}
