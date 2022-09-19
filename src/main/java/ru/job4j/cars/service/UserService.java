package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Account;
import ru.job4j.cars.repository.AccountRepository;

import java.util.Optional;

@Service
public class UserService {

    private final AccountRepository repository;

    public UserService(AccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> create(Account user) {
        return repository.create(user);
    }

    public Optional<Account> findByLoginAndPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }

    public boolean update(Account user) {
        return repository.update(user);
    }

    public boolean delete(long id) {
        return repository.delete(id);
    }
}
