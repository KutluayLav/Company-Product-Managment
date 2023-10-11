package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.dto.UserDto;
import com.kutluay.ProductManagment.model.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    void deleteUserByEmail(String email);

    void updateUser(UserDto userDto,long id);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
