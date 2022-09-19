package ru.job4j.cars.repository.details;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.details.transmission.TransmissionType;
import ru.job4j.cars.repository.QueryPerformer;

import java.util.List;

@Repository
public class TransmissionRepository {

    private final QueryPerformer executor;

    public TransmissionRepository(SessionFactory factory) {
        executor = new QueryPerformer(factory);
    }

    public List<TransmissionType> getTypes() {
        return executor.execute(session ->
                session.createQuery("SELECT type FROM TransmissionType").list());
    }

    public List<String> getDrives() {
        return executor.execute(session ->
                session.createQuery("SELECT drive FROM Drive ").list());
    }
}
