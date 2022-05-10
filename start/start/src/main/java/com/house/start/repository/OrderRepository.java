package com.house.start.repository;

import com.house.start.domain.Consumer;
import com.house.start.domain.Item;
import com.house.start.domain.Order;
import lombok.RequiredArgsConstructor;
import com.house.start.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByConsumer(Consumer consumer);

    Long countByOrderStatus(int orderStatus);

}
