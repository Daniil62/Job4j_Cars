package ru.job4j.cars.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type = "";
    private byte gears;

    public Transmission() {
    }

    public Transmission(int id, String type, byte gears) {
        this.id = id;
        this.type = type;
        this.gears = gears;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte getGears() {
        return gears;
    }

    public void setGears(byte gears) {
        this.gears = gears;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transmission)) {
            return false;
        }
        Transmission that = (Transmission) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String n = System.lineSeparator();
        return String.format("transmission:%s id: %d%s type: %s%s gears: %d%s",
                n, id, n, type, n, gears, n);
    }
}
