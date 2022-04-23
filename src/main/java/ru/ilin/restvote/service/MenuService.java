package ru.ilin.restvote.service;

import org.springframework.stereotype.Service;
import ru.ilin.restvote.repository.MenuRepository;

@Service
public class MenuService {
    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }
}
