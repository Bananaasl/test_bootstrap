package org.bootstrap.bootstrepdemo.controler;


import org.bootstrap.bootstrepdemo.model.User;
import org.bootstrap.bootstrepdemo.repos.RoleRepos;
import org.bootstrap.bootstrepdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepos roleRepos;

    @Autowired
    public AdminController(UserService userService, RoleRepos roleRepos) {
        this.userService = userService;
        this.roleRepos = roleRepos;
    }

    @GetMapping
    public String allUserTable(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("thisUser", userService.findByUsername(principal.getName()));
        model.addAttribute("roles", roleRepos.findAll());
        model.addAttribute("newUser", new User());
        return "users";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "user";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/del")
    public String deleteUser(@RequestParam(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
