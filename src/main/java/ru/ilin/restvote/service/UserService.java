package ru.ilin.restvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.restvote.model.User;
import ru.ilin.restvote.repository.UserRepository;

import java.util.List;

@Service("userService")
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(int id) {
        return repository.getById(id);
    }

    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = getById(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    public void delete(int id) {
        repository.delete(id);
    }
}
