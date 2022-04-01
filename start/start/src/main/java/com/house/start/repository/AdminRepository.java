package com.house.start.repository;

import com.house.start.domain.Admin;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class AdminRepository {
    
    private final EntityManager em;
    
    public AdminRepository(EntityManager em) {
        this.em = em;
    }
    
    public Long save(Admin admin) {
        em.persist(admin);
        return admin.getAdmin_id();
    }

    // 로그인 시, pwd
    // 로그인 후, name
    public Optional<Admin> findByAdmin_id(Long admin_id) {
        Admin admin = em.find(Admin.class, admin_id);
        return Optional.ofNullable(admin);
    }
}
