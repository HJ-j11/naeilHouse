package com.house.start.repository;

import com.house.start.domain.Delivery;
import com.house.start.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
