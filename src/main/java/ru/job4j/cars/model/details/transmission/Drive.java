package ru.job4j.cars.model.details.transmission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Drive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String drive = "";

    public Drive() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Drive)) {
            return false;
        }
        Drive other = (Drive) o;
        return id == other.id && drive.equals(other.drive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id) * (31 + drive.hashCode());
    }

    @Override
    public String toString() {
        return String.format("Drive: %s%s", drive, System.lineSeparator());
    }
}