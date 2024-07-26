package org.bootstrap.bootstrepdemo.controler;

import org.bootstrap.bootstrepdemo.model.User;
import org.bootstrap.bootstrepdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(Collections.singletonList(userService.findOne(id)));
    }

    @PostMapping()
    public ResponseEntity<List<User>> create(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping()
    public ResponseEntity<List<User>> update(@RequestBody User user) {
        if (user.getPassword() == null)
            user.setPassword(userService.findOne(user.getId()).getPassword());
        userService.update(user);
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.ok(userService.findAll());
    }
}
