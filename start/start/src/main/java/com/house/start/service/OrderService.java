package com.house.start.service;

import com.house.start.domain.Order;
import com.house.start.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    /**
     * 배송 취소
     */
    @Transactional
    public void cancel_delivery(Long orderId){
        // Order 정보 변경
        Order order = orderRepository.findOne(orderId);
        order.cancel();
        
        // Delivery 삭제

    }
}
