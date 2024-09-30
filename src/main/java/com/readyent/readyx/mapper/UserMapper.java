package com.readyent.readyx.mapper;

import com.readyent.readyx.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAllUsers();

    User findUserById(int id);

    void insertUser(User user);
}
