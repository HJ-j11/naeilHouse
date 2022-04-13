package com.house.start.repository;

import com.house.start.domain.Consumer;
import com.house.start.domain.Order;
import com.house.start.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
