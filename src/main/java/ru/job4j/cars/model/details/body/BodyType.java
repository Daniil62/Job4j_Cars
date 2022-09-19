package ru.job4j.cars.model.details.body;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BodyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String type = "";

    public BodyType() {
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BodyType)) {
            return false;
        }
        BodyType bodyType = (BodyType) o;
        return id == bodyType.id && type.equals(bodyType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id) * (31 + type.hashCode());
    }

    @Override
    public String toString() {
        return String.format(" type: %s%s", type, System.lineSeparator());
    }
}