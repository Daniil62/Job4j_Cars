package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fuelType = "";
    private float volume;
    private int horsepower;

    public Engine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Engine)) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        String n = System.lineSeparator();

        return String.format("id: %d%s type: %s%s volume: %f%s horsepower: %d%s",
                id, n, fuelType, n, volume, n, horsepower, n);
    }
}