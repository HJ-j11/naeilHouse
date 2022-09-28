package com.house.start.repository;


import com.house.start.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);

    @Override
    void delete(Role role);

}
