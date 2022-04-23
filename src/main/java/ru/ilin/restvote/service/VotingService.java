package ru.ilin.restvote.service;

import org.springframework.stereotype.Service;
import ru.ilin.restvote.repository.VotingRepository;

@Service
public class VotingService {
    private final VotingRepository repository;

    public VotingService(VotingRepository repository) {
        this.repository = repository;
    }
}
