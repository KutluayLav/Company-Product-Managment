package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.dto.UserDto;
import com.kutluay.ProductManagment.exception.UserNotFoundException;
import com.kutluay.ProductManagment.model.Role;
import com.kutluay.ProductManagment.model.User;
import com.kutluay.ProductManagment.repository.RoleRepository;
import com.kutluay.ProductManagment.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNo(userDto.getPhoneNo());


        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }


    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        user.getRoles().clear();
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new UserNotFoundException("Kullanıcı bulunamadı: " + email);
        }
    }

    @Override
    public void updateUser(UserDto userDto) {
        User existingUser = userRepository.findByEmail(userDto.getEmail());

        if (existingUser != null) {
            existingUser.setName(userDto.getFirstName() + " " + userDto.getLastName());
            existingUser.setPhoneNo(userDto.getPhoneNo());
            existingUser.setEmail(userDto.getEmail());


            userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException("Kullanıcı bulunamadı: " + userDto.getEmail());
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }
    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNo(user.getPhoneNo());
        return userDto;
    }
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }




}
