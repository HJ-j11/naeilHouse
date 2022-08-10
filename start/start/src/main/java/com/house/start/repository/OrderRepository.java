package com.house.start.repository;

import com.house.start.domain.*;
import com.house.start.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByMember(Member member);

    @Query("SELECT o.orderItems FROM Order o WHERE o.member = (:member)")
    List<OrderItem> findOrderItemsByConsumer(Member member);

}
