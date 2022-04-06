package com.sequra.disbursements.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Shopper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String nif;
    @OneToMany
    private List<Order> orders;

    public Shopper(String name, String email, String nif) {
        this.name = name;
        this.email = email;
        this.nif = nif;
    }

    public Shopper() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
