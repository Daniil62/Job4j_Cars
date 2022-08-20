package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name = "";
    private String login = "";
    private String password = "";
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Item> items = new HashSet<>();

    public User() {
    }

    public User(long id, String name, String login, String password, Set<Item> items) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.items = items;
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && name.equals(user.name) && login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return (Objects.hash(id) * (31 + name.hashCode())) * (31 + login.hashCode());
    }

    @Override
    public String toString() {
        String n = System.lineSeparator();
        return String.format("user: %s%s id: %d%s login: %s%s items:%s%s%s",
                name, n, id, n, login, n, n, items.toString(), n);
    }
}
