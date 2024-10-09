package com.example.sop.controllers;

import com.example.sop.dtos.OrderDto;
import com.example.sop.dtos.UserDto;
import com.example.sop.services.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public EntityModel<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        EntityModel<UserDto> userModel = EntityModel.of(userDto);

        userModel.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
        userModel.add(linkTo(methodOn(UserController.class).getUserOrders(id)).withRel("orders"));
        userModel.add(linkTo(methodOn(UserController.class).updateUser(id, userDto)).withRel("update"));
        userModel.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete"));

        return userModel;
    }

    @GetMapping
    public Iterable<EntityModel<UserDto>> getAllUsers() {
        return StreamSupport.stream(userService.getAll().spliterator(), false)
                .map(userDto -> {
                    EntityModel<UserDto> userModel = EntityModel.of(userDto);
                    userModel.add(linkTo(methodOn(UserController.class).getUserById(userDto.getId())).withSelfRel());
                    userModel.add(linkTo(methodOn(UserController.class).getUserOrders(userDto.getId())).withRel("orders"));
                    return userModel;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/orders")
    public List<OrderDto> getUserOrders(@PathVariable Long id) {
        return userService.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getOrder();
    }

    @PostMapping("/registryUser")
    public EntityModel<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.registry(userDto);
        return EntityModel.of(createdUser, linkTo(methodOn(UserController.class).getUserById(createdUser.getId())).withSelfRel());
    }

    @PutMapping("/update/{id}")
    public EntityModel<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        UserDto updatedUser = userService.update(userDto);
        return EntityModel.of(updatedUser, linkTo(methodOn(UserController.class)
                .getUserById(updatedUser.getId())).withSelfRel());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        userService.delete(id);

        UserDto updatedUser = userService.getById(id)
                .map(user -> {
                    user.add(linkTo(methodOn(UserController.class).getUserById(id))
                            .withSelfRel());
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return ResponseEntity.ok(updatedUser);
    }
}