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
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;
    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;
    @ManyToOne
    @JoinColumn(name = "body_id")
    private Body body;
    private int mileage;
    private byte ownersCount;

    public Vehicle() {
    }

    public Vehicle(long id, String name, Engine engine, Transmission transmission,
                   Body body, int mileage, byte ownersCount) {
        this.id = id;
        this.brand = name;
        this.engine = engine;
        this.transmission = transmission;
        this.body = body;
        this.mileage = mileage;
        this.ownersCount = ownersCount;
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

    public byte getOwnersCount() {
        return ownersCount;
    }

    public void setOwnersCount(byte ownersCount) {
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
        return String.format("vehicle: %s %s%s id: %d%s engine: %s%s transmission: %s%s"
                        + " body%s type: %s%s mileage: %d%s owners: %d%s",
                brand, model, n, id, n, engine.toString(), n, transmission.toString(),
                n, n, body.toString(), n, mileage, n, ownersCount, n);
    }
}
