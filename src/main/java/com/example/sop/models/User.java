package com.example.sop.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    private Long id;
    private String name;

    private List<Order> order;

    protected User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "user")
    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
