package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public String pageForAdmins(@ModelAttribute("user") User user, Model model, Principal principal) {
        Long id = userRepository.getUserByMail(principal.getName()).get().getId();
        model.addAttribute("admin", userService.getById(id));
        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }

    @PostMapping("/admin/newUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("allRoles", roleService.getRoles());
        return "redirect:/admin";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }
}
