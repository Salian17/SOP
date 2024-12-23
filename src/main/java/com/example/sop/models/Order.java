package com.example.sop.models;

import com.example.sop.enums.OrderStatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"order\"")
public class Order {
    private Long id;
    private OrderStatusEnum orderStatus;
    private User user;
    private List<MedicationOrder> medicationOrders;

    protected Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(value = EnumType.STRING)
    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "order")
    public List<MedicationOrder> getMedicationOrder() {
        return medicationOrders;
    }

    public void setMedicationOrder(List<MedicationOrder> medicationOrders) {
        this.medicationOrders = medicationOrders;
    }
}
