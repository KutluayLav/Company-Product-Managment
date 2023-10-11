package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.dto.UserDto;
import com.kutluay.ProductManagment.model.User;
import com.kutluay.ProductManagment.repository.RoleRepository;
import com.kutluay.ProductManagment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        roleRepository = mock(RoleRepository.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder, roleRepository);
    }

   /* @Test
    public void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john@example.com");
        userDto.setPhoneNo("123456789");

        User existingUser = new User();
        existingUser.setName("Jane Doe");
           existingUser.setEmail("john@example.com");

        when(userRepository.findByEmail("john@example.com")).thenReturn(existingUser);

        userService.updateUser(user);

        assertEquals("John Doe", existingUser.getName());
    }*/

}