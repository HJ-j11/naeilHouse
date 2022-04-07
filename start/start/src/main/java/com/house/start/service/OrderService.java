package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.repository.ConsumerRepository;
import com.house.start.repository.ItemRepository;
import com.house.start.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ConsumerRepository consumerRepository;

    /**
     *  주문 (바로구매)
     */
    @Transactional
    public Long order(Long consumerId, Long itemId, int count) {

        // 엔티티 조회 (소비자 정보 + 상품 정보)
        Consumer consumer = consumerRepository.findConsumer(consumerId);
        Item item = itemRepository.findItem(itemId);

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




}
