package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.house.start.domain.Delivery;
import com.house.start.domain.DeliveryStatus;
import com.house.start.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final ItemRepository itemRepository;
    private final ConsumerRepository consumerRepository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;

    /**
     *  주문 (바로 구매)
     */
    @Transactional
    public Long order(Long consumerId, Long itemId, int count) {

        // 엔티티 조회 (소비자 정보 + 상품 정보)
        Consumer consumer = consumerRepository.findById(consumerId).get();
        log.info("1 consumer id : " + consumer.getId());
        Item item = itemRepository.findById(itemId).get();
        log.info("1 item id : " + item.getId());

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress("서울");
        delivery.setDeliveryStatus(DeliveryStatus.PREPARING);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        orderItem.setOrderItemStatus(OrderItemStatus.COMPLETED);
        log.info("orderItem id : " + orderItem.getId());

        // 주문 생성
        Order order = Order.createOrder(consumer, delivery, orderItem);
        log.info("order id : " + order.getId());

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    public List<Order> findOrderByConsumer (Consumer consumer) {
        return orderRepository.findByConsumer(consumer);
    }

    public List<OrderItem> findOrderItemByConsumer(Consumer consumer) {
        return orderRepository.findOrderItemsByConsumer(consumer);
    }

    // 장바구니에 있는 상품 구매
    @Transactional
    public Long orders(Consumer consumer) {
        Cart cart = cartRepository.findByConsumer(consumer);

        Order order = Order.builder()
                .consumer(consumer)
                .build();

        for (CartItem cartItem: cart.getCartItems()) {
            Item itemInCart = cartItem.getItem();
            OrderItem orderItem = OrderItem.createOrderItem(itemInCart, itemInCart.getPrice(), cartItem.getCount());
            orderItem.setOrderItemStatus(OrderItemStatus.COMPLETED);
            order.addOrderItem(orderItem);

            Delivery delivery = Delivery.builder()
                    .deliveryStatus(DeliveryStatus.PREPARING)
                    .build();
            orderItem.setDelivery(delivery);

        }

        orderRepository.save(order);
        return order.getId();
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOrder(Long id) { return orderRepository.findById(id);}

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderitem_id){
        // Order 정보 변경
        OrderItem orderItem = orderItemRepository.findById(orderitem_id)
                .orElse(null);
        Delivery delivery = orderItem.getDelivery();
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        orderItem.setOrderItemStatus(OrderItemStatus.CANCELED);

        // Item 수량 변경
        Item canceledItem = orderItem.getItem();
        canceledItem.setStockQuantity(canceledItem.getStockQuantity() + orderItem.getCount());

        // Delivery 삭제
        deliveryRepository.delete(delivery);
    }
    /**
     * Order과 연결된 OrderItem의 상태 갯수 반환
     * @param orders Order객체의 리스트
     * @return COMPLETED, ORDER 순의 Orderitem 객체의 갯수
     */
    public List<Long> countStatus(List<Order> orders) {
        List<Long> statusList = new ArrayList<>();
        Long completeStatus = 0L;
        Long orderStatus = 0L;
        for(Order order: orders){
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem: orderItems) {
                if (orderItem.getOrderItemStatus() == OrderItemStatus.COMPLETED) {
                    completeStatus = completeStatus + 1L;
                }
                else if (orderItem.getOrderItemStatus() == OrderItemStatus.ORDER) {
                    orderStatus = orderStatus + 1L;
                }
            }
        }
        statusList.add(completeStatus);
        statusList.add(orderStatus);
        return statusList;
    }
}
