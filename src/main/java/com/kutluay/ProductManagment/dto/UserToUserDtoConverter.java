package com.kutluay.ProductManagment.dto;

import com.kutluay.ProductManagment.model.User;

public class UserToUserDtoConverter {

    public static UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());

        String[] nameParts = user.getName().split(" ");
        if (nameParts.length > 0) {
            userDto.setFirstName(nameParts[0]);
        }
        if (nameParts.length > 1) {
            userDto.setLastName(nameParts[1]);
        }
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNo(user.getPhoneNo());

        return userDto;
    }

}
