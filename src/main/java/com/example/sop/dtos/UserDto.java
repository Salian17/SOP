package com.example.sop.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class UserDto extends RepresentationModel<UserDto> {
    private Long id;
    private String name;
    private List<Long> orderId;
    protected UserDto() {}

    public UserDto(Long id, String name, List<Long> orderId) {
        this.id = id;
        this.name = name;
        this.orderId = orderId;
    }

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

    public List<Long> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Long> orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
