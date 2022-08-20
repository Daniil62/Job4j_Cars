package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type = "";
    private byte volume;

    public Engine() {
    }

    public Engine(int id, String type, byte volume) {
        this.id = id;
        this.type = type;
        this.volume = volume;
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

    public byte getVolume() {
        return volume;
    }

    public void setVolume(byte volume) {
        this.volume = volume;
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

        return String.format("engine:%s id: %d%s type: %s%s volume: %d%s",
                n, id, n, type, n, volume, n);
    }
}
