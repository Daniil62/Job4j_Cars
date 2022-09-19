package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.utils.QueryFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AdRepository {

    private final Performer executor;

    private static final String FIND_ITEMS_BASE_QUERY_BODY =
            "SELECT DISTINCT a FROM Ad a "
            + "JOIN FETCH a.vehicle v "
            + "JOIN FETCH v.body "
            + "JOIN FETCH v.engine "
            + "JOIN FETCH v.transmission "
            + "JOIN FETCH a.account ac";

    public AdRepository(SessionFactory factory) {
        this.executor = new QueryPerformer(factory);
    }

    public Ad create(Ad ad) {
        return executor.execute(session -> {
            session.persist(ad);
            return ad;
        });
    }

    public List<Ad> findAll() {
        return executor.execute(session ->
                session.createQuery("FROM Ad").list());
    }

    public List<Ad> findAllActive() {
        return executor.execute(session -> session.createQuery(
                        FIND_ITEMS_BASE_QUERY_BODY
                                + " WHERE a.isSold = false", Ad.class).list());
    }

    public List<Ad> findWithFilters(QueryFilter<Ad> filter) {
        return (executor.execute(session ->
                filter.getQuery(session, FIND_ITEMS_BASE_QUERY_BODY).list()));
    }

    public Optional<Ad> findById(long id) {
        return Optional.of(executor.execute(session -> session.get(Ad.class, id)));
    }

    public List<Ad> findByLastDay() {
        return executor.execute(session -> session.createQuery(
                FIND_ITEMS_BASE_QUERY_BODY
                        + " WHERE a.created >= :arg", Ad.class)
                .setParameter("arg", LocalDateTime.now().minusDays(1))
                .list());
    }

    public List<Ad> findWithPhoto() {
        return executor.execute(session -> session.createQuery(
                FIND_ITEMS_BASE_QUERY_BODY
                        + " WHERE a.photo IS NOT EMPTY", Ad.class).list());
    }

    public List<Ad> findByBrand(String brand) {
        return executor.execute(session -> session.createQuery(
                FIND_ITEMS_BASE_QUERY_BODY
                        + " WHERE v.brand LIKE :arg", Ad.class)
                .setParameter("arg", brand).list());
    }

    public List<Ad> findByUser(long userId) {
        return executor.execute(session -> session.createQuery(
                FIND_ITEMS_BASE_QUERY_BODY
                        + " WHERE ac.id = :arg", Ad.class)
                        .setParameter("arg", userId).list());
    }

    public void update(Ad newAd) {
        executor.execute(session -> {
            session.update(newAd);
            return newAd;
        });
    }

    public boolean changeStatus(long id, boolean value) {
        return executor.execute(session ->
                session.createQuery("UPDATE Ad a SET a.isSold = :val WHERE a.id= :id")
                        .setParameter("val", value)
                        .setParameter("id", id).executeUpdate() > 0);
    }

    public boolean delete(long id) {
        return executor.execute(session ->
                session.createQuery("DELETE FROM Ad where id = :id")
                        .setParameter("id", id).executeUpdate() > 0);
    }

    public byte[] getAdPhoto(long id) {
        return (byte[]) executor.execute(session ->
                session.createQuery("SELECT photo FROM Ad a WHERE a.id = :id")
                        .setParameter("id", id).getSingleResult());
    }
}