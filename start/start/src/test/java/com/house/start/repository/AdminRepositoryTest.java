package com.house.start.repository;

import com.house.start.domain.Admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AdminRepositoryTest {
    @Autowired AdminRepository adminRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdmin() {
        Admin admin = new Admin();
        admin.setId("admin");
        admin.setName("admin");
        admin.setPwd("1234");
        Long savedId = adminRepository.save(admin);

        Optional<Admin> findAdmin = adminRepository.findByAdmin_id(savedId);
    }
}