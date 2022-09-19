package ru.job4j.cars.repository.details;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.details.engine.EngineType;
import ru.job4j.cars.repository.QueryPerformer;

import java.util.List;

@Repository
public class EngineRepository {

    private final QueryPerformer executor;

    public EngineRepository(SessionFactory factory) {
        executor = new QueryPerformer(factory);
    }

    public List<EngineType> getTypes() {
        return executor.execute(session ->
                session.createQuery("SELECT fuelType FROM EngineType").list());
    }
}
