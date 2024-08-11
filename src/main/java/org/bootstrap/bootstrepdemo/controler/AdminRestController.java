package org.bootstrap.bootstrepdemo.controler;

import org.bootstrap.bootstrepdemo.model.Role;
import org.bootstrap.bootstrepdemo.model.User;
import org.bootstrap.bootstrepdemo.repos.RoleRepos;
import org.bootstrap.bootstrepdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {


    private final UserService userService;
    private final RoleRepos roleRepos;
    @Autowired
    public AdminRestController(UserService userService, RoleRepos roleRepos) {
        this.userService = userService;
        this.roleRepos = roleRepos;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findOne(id));
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleRepos.findAll());
    }

    @PostMapping()
    public ResponseEntity<List<User>> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping()
    public ResponseEntity<List<User>> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(userService.findAll());
    }
}
