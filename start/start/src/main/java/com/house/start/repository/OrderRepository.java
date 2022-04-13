package com.house.start.repository;

import com.house.start.domain.Order;
import lombok.RequiredArgsConstructor;
import com.house.start.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    private final EntityManager em;
//
//    private void  save(Order order);
//    {
//        em.persist(order);
//    }
    List<Order> findCartOrder(Long consumerId);

//    public List<Order> findCartOrder(Long consumerId) {
//        return em.createQuery("select o from Order o where o.orderStatus = 'CART'", Order.class)
//                .getResultList();
//    }

}
