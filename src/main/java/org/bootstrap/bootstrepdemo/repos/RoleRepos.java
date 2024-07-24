package org.bootstrap.bootstrepdemo.repos;


import org.bootstrap.bootstrepdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepos extends JpaRepository<Role, Long> {
}
