package com.example.sop.datafetchers;

import com.example.sop.dtos.UserDto;
import com.example.sop.models.User;
import com.example.sop.repositories.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class UsersDataFetcher {
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public UsersDataFetcher(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @DgsQuery
    public UserDto userById(@InputArgument Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? modelMapper.map(user, UserDto.class) : null;
    }
    @DgsQuery
    public List<UserDto> users(@InputArgument String nameFilter) {
        List<User> queryResult;
        if (nameFilter == null) {
            queryResult = userRepository.findAll();
        } else {
            queryResult = userRepository.findByName(nameFilter);
        }
        return queryResult.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
    @DgsMutation
    public UserDto addUser(@InputArgument UserDto user) {
        User newUser = modelMapper.map(user, User.class);
        newUser = userRepository.save(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }
    @DgsMutation
    public UserDto updateUser(@InputArgument Long id, @InputArgument(name="user") UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setName(userDto.getName());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }
    @DgsMutation
    public boolean deleteUser(@InputArgument Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
