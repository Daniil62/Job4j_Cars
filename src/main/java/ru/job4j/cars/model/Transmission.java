package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type = "";
    private int gears;
    private String drive = "";

    public Transmission() {
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

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
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
        return String.format("id: %d%s type: %s%s gears: %d%s drive: %s%s",
                id, n, type, n, gears, n, drive, n);
    }
}