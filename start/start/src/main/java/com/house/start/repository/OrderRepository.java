package com.house.start.repository;

import com.house.start.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public List<Order> findCartOrder(Long consumerId) {
        return em.createQuery("select o from Order o where o.orderStatus = 'CART'", Order.class)
                .getResultList();
    }

}
