package org.bootstrap.bootstrepdemo.init;


import org.bootstrap.bootstrepdemo.model.Role;
import org.bootstrap.bootstrepdemo.model.User;
import org.bootstrap.bootstrepdemo.repos.RoleRepos;
import org.bootstrap.bootstrepdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AddUserAndRoleInTable {

    private final RoleRepos roleRepos;
    private final UserService userService;

    @Autowired
    public AddUserAndRoleInTable(RoleRepos roleRepos, UserService userService) {
        this.roleRepos = roleRepos;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        roleRepos.save(new Role(1L, "ROLE_ADMIN"));
        roleRepos.save(new Role(2L, "ROLE_USER"));
        userService.save(new User("Anar", "banan_asl@icloud.com", "12345678",roleRepos.findAll()));
        userService.save(new User("Andrey", "Andrey@icloud.com", "1234", roleRepos.findById(2L).stream().toList()));
    }
}
