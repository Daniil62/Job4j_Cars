package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Ad;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AdRepository {

    private final Executor executor;

    private static final String FIND_ITEMS_BASE_QUERY_BODY =
            "SELECT DISTINCT i FROM Ad i "
            + "JOIN FETCH i.vehicle v "
            + "JOIN FETCH v.body "
            + "JOIN FETCH v.engine "
            + "JOIN FETCH v.transmission "
            + "JOIN FETCH i.user";

    public AdRepository(SessionFactory factory) {
        this.executor = new QueryExecutor(factory);
    }

    public List<Ad> findByLastDay() {
        return (executor.execute(session -> session.createQuery(
                FIND_ITEMS_BASE_QUERY_BODY
                        + " WHERE i.created >= :arg", Ad.class))
                .setParameter("arg", LocalDateTime.now().minusDays(1))
                .list());
    }

    public List<Ad> findWithPhoto() {
        return (executor.execute(session -> session.createQuery(
                FIND_ITEMS_BASE_QUERY_BODY
                        + " WHERE i.photo.size > :arg", Ad.class))
                .setParameter("arg", 0)
                .list());
    }

    public List<Ad> findByBrand(String brand) {
        return (executor.execute(session -> session.createQuery(
                FIND_ITEMS_BASE_QUERY_BODY
                        + " WHERE v.brand = :arg", Ad.class))
                .setParameter("arg", brand)
                .list());
    }
}