package com.wip.smartparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wip.smartparking.dto.request.UserRequestDTO;
import com.wip.smartparking.dto.response.UserResponseDTO;
import com.wip.smartparking.entity.User;
import com.wip.smartparking.mapper.UserMapper;
import com.wip.smartparking.service.UserService;

import jakarta.validation.Valid;
/**
 * REST Controller exposing API endpoints for CRUD and business operations on User resources.
 *
 * @author Naveen Muthu
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDTO saveUser(@Valid @RequestBody UserRequestDTO dto) {

        User user = UserMapper.toEntity(dto);
        User savedUser = userService.saveUser(user);

        return UserMapper.toResponseDTO(savedUser);
    }

    @GetMapping("getAUser/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {

        User user = userService.getUserById(id);

        return UserMapper.toResponseDTO(user);
    }

    @GetMapping("/listAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}