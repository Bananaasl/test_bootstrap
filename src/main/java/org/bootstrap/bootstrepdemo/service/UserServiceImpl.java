package org.bootstrap.bootstrepdemo.service;


import org.bootstrap.bootstrepdemo.model.User;
import org.bootstrap.bootstrepdemo.repos.UserRepos;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepos userRepos;

    public UserServiceImpl(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepos.findAll();
    }

    @Override
    @Transactional
    public User findOne(Long id) {
        Optional<User> user = userRepos.findById(id);
        return user.orElse(null);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepos.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepos.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepos.deleteById(id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepos.findByUsername(username);
    }
}
