package com.house.start.repository;

import com.house.start.domain.Item;
import com.house.start.domain.Order;
import com.house.start.domain.OrderItem;
import com.house.start.domain.OrderItemStatus;
import com.house.start.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o.order FROM OrderItem o WHERE o.item IN (:items)")
    List<Order> findOrderItemsByItems(@Param("items") List<Item> items);

    @Query("SELECT o FROM OrderItem  o WHERE o.orderItemStatus = :orderItemStatus AND o.item.member = :member")
    List<OrderItem> findOrderItemsBySeller(@Param("orderItemStatus") OrderItemStatus orderItemStatus, @Param("member") Member member);

    List<OrderItem> findOrderItemByOrder(Order order);
}
