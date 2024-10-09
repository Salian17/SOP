package com.example.sop.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class UserDto extends RepresentationModel<UserDto> {
    private Long id;
    private String name;
    private List<OrderDto> order;
    protected UserDto() {}

    public UserDto(Long id, String name, List<OrderDto> order) {
        this.id = id;
        this.name = name;
        this.order = order;
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

    public List<OrderDto> getOrder() {
        return order;
    }

    public void setOrder(List<OrderDto> order) {
        this.order = order;
    }
}
