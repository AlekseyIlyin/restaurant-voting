package ru.ilin.restvote.service;

import org.springframework.stereotype.Service;
import ru.ilin.restvote.model.User;
import ru.ilin.restvote.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }
}
