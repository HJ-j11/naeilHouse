package com.house.start.repository;

import com.house.start.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OrderRepository {
    private final EntityManager em;

    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    public Order findOne(Long order_id) {
        return em.find(Order.class, order_id);
    }

}
