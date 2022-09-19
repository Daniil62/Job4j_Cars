package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Account;

import java.util.Optional;

@Repository
public class AccountRepository {

    private final Performer executor;

    public AccountRepository(SessionFactory factory) {
        this.executor = new QueryPerformer(factory);
    }

    public Optional<Account> create(Account account) {
        try {
            executor.execute(session -> session.save(account));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return account.getId() == 0 ? Optional.empty() : Optional.of(account);
    }

    public Optional<Account> findByLoginAndPassword(String login, String password) {
        Optional<Account> result = Optional.empty();
        try {
        result = Optional.of((Account) executor.execute(session ->
                session.createQuery("FROM Account WHERE login = :log AND password = :pwd")
                        .setParameter("log", login)
                        .setParameter("pwd", password)
                        .getSingleResult()));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(Account account) {
        return executor.execute(session ->
                session.createQuery("UPDATE Account a SET a.name = :name, "
                        + "a.login = :log, a.password = :pwd, a.ads = :ads WHERE a.id = :id")
                        .setParameter("name", account.getName())
                        .setParameter("log", account.getLogin())
                        .setParameter("pwd", account.getPassword())
                        .setParameter("ads", account.getAds())
                        .setParameter("id", account.getId())
                        .executeUpdate() > 0);
    }

    public boolean delete(long id) {
        return executor.execute(session ->
                session.createQuery("DELETE Account WHERE id = :id")
                        .setParameter("id", id)
                        .executeUpdate() > 0);
    }
}