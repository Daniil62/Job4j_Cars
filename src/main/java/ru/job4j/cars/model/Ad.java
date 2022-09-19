package ru.job4j.cars.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String header = "";
    private String description = "";
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle = new Vehicle();
    private boolean isSold;
    private byte[] photo = new byte[]{};
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account = new Account();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created = LocalDateTime.now();
    private double price;

    public Ad() {
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

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getFormCreated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return getCreated().format(formatter);
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ad)) {
            return false;
        }
        Ad ad = (Ad) o;
        return id == ad.id && header.equals(ad.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id) * (31 + header.hashCode());
    }

    @Override
    public String toString() {
        String n = System.lineSeparator();
        return String.format("Item %s header: %s%s description: %s%s%s vehicle: "
                        + "%s%s%s is sales: %s%s owner: %s%s%s created: %s%s price: %f%s",
                n, header, n, n, description, n, n, vehicle.toString(),
                n, isSold ? "yes" : "no", n, n, account.toString(), n, created, n, price, n);
    }
}