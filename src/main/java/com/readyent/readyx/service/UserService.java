package com.readyent.readyx.service;

import com.readyent.readyx.domain.entity.User;
import com.readyent.readyx.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    public User getUserById(int id) {
        return userMapper.findUserById(id);
    }

    public void addUser(User user) {
        userMapper.insertUser(user);
    }
}
