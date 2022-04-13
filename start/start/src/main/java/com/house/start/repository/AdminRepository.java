package com.house.start.repository;

import com.house.start.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
