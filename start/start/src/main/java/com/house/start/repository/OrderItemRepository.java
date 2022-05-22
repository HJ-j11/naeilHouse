package com.house.start.repository;

import com.house.start.domain.Item;
import com.house.start.domain.Order;
import com.house.start.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o.order FROM OrderItem o WHERE o.item IN (:items)")
    List<Order> findOrderItemsByItems(@Param("items") List<Item> items);

}
