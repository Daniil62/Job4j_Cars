package ru.job4j.cars.model.details.engine;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class EngineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String fuelType = "";

    public EngineType() {
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EngineType)) {
            return false;
        }
        EngineType that = (EngineType) o;
        return id == that.id && fuelType.equals(that.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id) * (31 + fuelType.hashCode());
    }

    @Override
    public String toString() {
        return String.format(" fuel type: %s%s", fuelType, System.lineSeparator());
    }
}