package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand = "";
    private String model = "";
    private int year;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "engine_id")
    private Engine engine;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "body_id")
    private Body body;
    private int mileage;
    private int ownersCount;

    public Vehicle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getOwnersCount() {
        return ownersCount;
    }

    public void setOwnersCount(int ownersCount) {
        this.ownersCount = ownersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && brand.equals(vehicle.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id) * (31 + brand.hashCode());
    }

    @Override
    public String toString() {
        String n = System.lineSeparator();
        return String.format("Vehicle: %s %s%s year: %d%s id: %d%s engine: %s%s transmission: %s%s"
                        + " body%s type: %s%s mileage: %d%s owners: %d%s",
                brand, model, n, year, n, id, n, engine.toString(), n, transmission.toString(),
                n, n, body.toString(), n, mileage, n, ownersCount, n);
    }
}