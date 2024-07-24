package org.bootstrap.bootstrepdemo.service;


import org.bootstrap.bootstrepdemo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findOne(Long id);
    void save(User user);
    void update(User user);
    void delete(Long id);
    User findByUsername(String username);
}
