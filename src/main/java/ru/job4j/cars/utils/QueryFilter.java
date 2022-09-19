package ru.job4j.cars.utils;

import org.hibernate.Session;
import org.hibernate.query.Query;

public interface QueryFilter<T> {

    Query<T> getQuery(Session session, String baseQuery);
}
