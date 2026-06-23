package com.wip.smartparking.ui;

import com.wip.smartparking.service.UserService;
import com.wip.smartparking.dto.request.UserRequestDTO;
import com.wip.smartparking.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;																										
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
/**
 * UI Controller for handling views, dashboard routing, and user interactions related to User resources.
 *
 * @author althaf
 */

@Controller
@RequestMapping("/ui/users")
public class UserUIController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("user", new UserRequestDTO());
        return "user/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("user") UserRequestDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return "user/form";
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        userService.saveUser(user);
        return "redirect:/ui/users";
    }
}