package com.house.start.repository;

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

    // consumer id로 조회하는 것 같아서 수정했는데 맞을까요..?
    List<Order> findByConsumer(Long consumerId);

}
