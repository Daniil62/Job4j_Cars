package ru.job4j.cars.repository;

import org.hibernate.Session;

import java.util.function.Function;

public interface Performer {

    public <T> T execute(final Function<Session, T> command);
}
