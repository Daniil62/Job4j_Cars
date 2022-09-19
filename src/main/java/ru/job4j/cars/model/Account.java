package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "account",
        uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name = "";
    @Column(unique = true, nullable = false)
    private String login = "";
    @Column(nullable = false)
    private String password = "";
    @Column(nullable = false)
    private String phone = "";
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ad> ads = new ArrayList<>();

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Account)) {
            return false;
        }
        Account user = (Account) o;
        return id == user.id && name.equals(user.name) && login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return (Objects.hash(id) * (31 + name.hashCode())) * (31 + login.hashCode());
    }

    @Override
    public String toString() {
        String n = System.lineSeparator();
        return String.format("Account: %s%s id: %d%s login: %s%s phone: %s%s",
                name, n, id, n, login, n, phone, n);
    }
}