package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.house.start.domain.Delivery;
import com.house.start.domain.DeliveryStatus;
import com.house.start.domain.Order;
import com.house.start.domain.OrderStatus;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final ItemRepository itemRepository;
    private final ConsumerRepository consumerRepository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final CartRepository cartRepository;
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

    // 장바구니에 있는 상품 구매
    @Transactional
    public Long orders(Consumer consumer) {
        Cart cart = cartRepository.findByConsumer(consumer);
        Delivery delivery = Delivery
                .builder()
                .deliveryStatus(DeliveryStatus.PREPARING)
                .build();
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem: cart.getCartItems()) {
            OrderItem orderItem = OrderItem.createOrderItem(cartItem.getItem(), cartItem.getCount(), cartItem.getCount());
            orderItems.add(orderItem);
        }

        Order order = Order.createOrders(consumer, delivery, orderItems);
        delivery.setOrder(order);

        orderRepository.save(order);
        return order.getId();
    }

    public List<Order> findCartOrder(Consumer consumer) {
        return orderRepository.findByConsumer(consumer);
    } // 나중에 이름 바꾸기

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOrder(Long id) { return orderRepository.findById(id);}

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

    public Long countOrderStaus() {
        OrderStatus orderStatus = OrderStatus.COMPLETE;
        return orderRepository.countByOrderStatus(OrderStatus.ORDER);
    }

    public Long countCompleteStaus() {
        return orderRepository.countByOrderStatus(OrderStatus.COMPLETE);
    }
}
