package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String header = "";
    private String description = "";
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private boolean isSales;
    private byte[] photo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item() {
    }

    public Item(long id, String header, String description,
                Vehicle vehicle, boolean isSales, byte[] photo, User user) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.vehicle = vehicle;
        this.isSales = isSales;
        this.photo = photo;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isSales() {
        return isSales;
    }

    public void setSales(boolean sales) {
        isSales = sales;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id && header.equals(item.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id) * (31 + header.hashCode());
    }

    @Override
    public String toString() {
        String n = System.lineSeparator();
        return String.format("Item %s header: %s%s description: %s%s%s vehicle: "
                        + "%s%s%s is sales: %s%s owner: %s%s%s",
                n, header, n, n, description, n, n, vehicle.toString(),
                n, isSales ? "yes" : "no", n, n, user.toString(), n);
    }
}
