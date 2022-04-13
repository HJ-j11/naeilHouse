package com.house.start.service;

import com.house.start.domain.Delivery;
import com.house.start.domain.DeliveryStatus;
import com.house.start.domain.Order;
import com.house.start.domain.OrderStatus;
import com.house.start.repository.DeliveryRepository;
import com.house.start.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancel_delivery(Long orderId){
        // Order 정보 변경
        Order order = orderRepository.findOne(orderId);
        Delivery delivery = order.getDelivery();
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        order.setOrderStatus(OrderStatus.CANCEL);

        // Delivery 삭제

        deliveryRepository.deleteDelivery(delivery);
    }


}
