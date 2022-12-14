package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "body")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type = "";
    private String color = "";

    public Body() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Body)) {
            return false;
        }
        Body body = (Body) o;
        return id == body.id && type.equals(body.type) && color.equals(body.color);
    }

    @Override
    public int hashCode() {
        return (Objects.hash(id)
                * (31 + type.hashCode())) * (31 + color.hashCode());
    }

    @Override
    public String toString() {
        String n = System.lineSeparator();
        return String.format("id: %d%s type: %s%s color: %s%s",
                id, n, type, n, color, n);
    }
}