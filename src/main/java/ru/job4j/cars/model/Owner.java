package ru.job4j.cars.model;

import javax.persistence.*;

public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
