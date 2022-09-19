package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

public class QueryPerformer implements Performer {

    private final SessionFactory factory;

    public QueryPerformer(SessionFactory factory) {
        this.factory = factory;
    }

    public <T> T execute(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}