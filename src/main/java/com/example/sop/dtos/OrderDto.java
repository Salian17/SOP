package com.example.sop.dtos;

import com.example.sop.enums.OrderStatusEnum;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private OrderStatusEnum orderStatus;
    private Long userId;
    private List<Long> orderId = new ArrayList<>();

    protected OrderDto() {}

    public OrderDto(Long id, OrderStatusEnum orderStatus, Long userId, List<Long> orderId) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.orderId = orderId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Long> orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}
