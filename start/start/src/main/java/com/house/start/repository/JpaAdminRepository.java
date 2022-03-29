package com.house.start.repository;

import com.house.start.domain.Admin;

import javax.persistence.EntityManager;
import java.util.Optional;

public class JpaAdminRepository {
    
    private final EntityManager em;
    
    public JpaAdminRepository(EntityManager em) {
        this.em = em;
    }
    
    public Admin save(Admin admin) {
        em.persist(admin);
        return admin;
    }

    // 로그인 시, pwd
    // 로그인 후, name
    public Optional<Admin> findByAdmin_id(String admin_id) {
        Admin admin = em.find(Admin.class, admin_id);
        return Optional.ofNullable(admin);
    }
}
