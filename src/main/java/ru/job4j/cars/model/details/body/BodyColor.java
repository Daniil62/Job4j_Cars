package ru.job4j.cars.model.details.body;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BodyColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String color = "";
    @Column(unique = true, nullable = false)
    private String hex = "";

    public BodyColor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BodyColor)) {
            return false;
        }
        BodyColor bodyColor = (BodyColor) o;
        return id == bodyColor.id && color.equals(bodyColor.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id) * (31 + color.hashCode());
    }

    @Override
    public String toString() {
        String n = System.lineSeparator();
        return String.format(" color: %s%s HEX: %s%s", color, n, hex, n);
    }
}