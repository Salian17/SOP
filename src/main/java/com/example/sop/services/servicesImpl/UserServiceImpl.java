package com.example.sop.services.servicesImpl;

import com.example.sop.dtos.UserDto;
import com.example.sop.models.User;
import com.example.sop.repositories.UserRepository;
import com.example.sop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    final private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<UserDto> getAll() {
        return userRepository.findAll().stream().map(s -> modelMapper.map(s, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(id), UserDto.class));
    }

    @Override
    public UserDto registry(UserDto user) {
        return modelMapper.map(userRepository.saveAndFlush(modelMapper
                .map(user, User.class)), UserDto.class);
    }

    @Override
    public UserDto update(UserDto user) {
        return modelMapper.map(userRepository.saveAndFlush(modelMapper
                .map(user, User.class)), UserDto.class);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
