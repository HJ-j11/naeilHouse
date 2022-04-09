package com.house.start.repository;

import com.house.start.domain.Order;
import com.house.start.domain.Seller;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderRepository {
    private final EntityManager em;

    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    /*
    주문 전체 목록 조회
     */
    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    /*
    order_id 하나의 객체만 조회
     */
    public Order findOne(Long order_id) {
        return em.find(Order.class, order_id);
    }

}
