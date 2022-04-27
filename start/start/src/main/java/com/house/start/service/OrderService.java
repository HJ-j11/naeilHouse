package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.repository.ConsumerRepository;
import com.house.start.repository.ItemRepository;
import com.house.start.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.house.start.domain.Delivery;
import com.house.start.domain.DeliveryStatus;
import com.house.start.domain.Order;
import com.house.start.domain.OrderStatus;
import com.house.start.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final EntityManager em;
    private final ItemRepository itemRepository;
    private final ConsumerRepository consumerRepository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    /**
     *  주문 (바로 구매)
     */
    @Transactional
    public Long order(Long consumerId, Long itemId, int count) {

        // 엔티티 조회 (소비자 정보 + 상품 정보)
        Consumer consumer = consumerRepository.findById(consumerId).get();
        Item item = itemRepository.findById(itemId).get();

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress("서울");
        delivery.setDeliveryStatus(DeliveryStatus.PREPARING);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(consumer, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    public List<Order> findCartOrder(Long consumerId) {
        return orderRepository.findByConsumer(consumerId);
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long order_id){
        // Order 정보 변경
        Order order = orderRepository.findById(order_id).get();
        Delivery delivery = order.getDelivery();
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        order.setOrderStatus(OrderStatus.CANCEL);

        // Item 수량 변경
        for (OrderItem orderItem : order.getOrderItems()) {
            Item canceledItem = orderItem.getItem();
            canceledItem.setStockQuantity(canceledItem.getStockQuantity() + orderItem.getCount());
        }

        // Delivery 삭제
        deliveryRepository.delete(delivery);
    }
}
