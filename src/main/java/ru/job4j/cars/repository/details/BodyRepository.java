package ru.job4j.cars.repository.details;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.details.body.BodyColor;
import ru.job4j.cars.model.details.body.BodyType;
import ru.job4j.cars.repository.QueryPerformer;

import java.util.List;

@Repository
public class BodyRepository {

    private final QueryPerformer executor;

    public BodyRepository(SessionFactory factory) {
        executor = new QueryPerformer(factory);
    }

    public List<BodyType> getTypes() {
        return executor.execute(session ->
                session.createQuery("SELECT type FROM BodyType").list());
    }

    public List<BodyColor> getColors() {
        return executor.execute(session ->
                session.createQuery("SELECT color FROM BodyColor").list());
    }
}
