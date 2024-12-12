package com.example.sop.dtos;

import com.example.sop.enums.OrderStatusEnum;
import com.example.sop.models.User;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class OrderDto extends RepresentationModel<OrderDto> {
    private Long id;
    private OrderStatusEnum orderStatus;
    private User user;
    private List<MedicationOrderDto> orderItems = new ArrayList<>();

    protected OrderDto() {}

    public OrderDto(Long id, OrderStatusEnum orderStatus, User user, List<MedicationOrderDto> orderItems) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.user = user;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MedicationOrderDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<MedicationOrderDto> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\n    id=" + id +
                ",\n    orderStatus=" + orderStatus +
                ",\n    user=" + user +
                ",\n    orderItems=" + orderItems +
                "\n}";
    }
}
