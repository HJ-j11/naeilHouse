package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired ConsumerRepository consumerRepository;
    @Autowired SellerRepository sellerRepository;
    @Autowired ItemRepository itemRepository;
    @Autowired CategoryRepository categoryRepository;
    @Autowired CartRepository cartRepository;

    @Test
    public void 주문취소() throws Exception {
        //Given
        Consumer consumer = createConsumer();
        Item item = createItem(10);
        int orderCount = 2;
        Long orderId = orderService.order(consumer.getId(), item.getId(), orderCount);

        //When
        orderService.cancelOrder(orderId);

        //Then
        Order order = orderRepository.findById(orderId).get();
        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, order.getOrderStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }

    @Test
    public void 주문완료() throws Exception {
        //Given
        Consumer consumer = createConsumer();

        // when
        Long orderId = createOrder(consumer);

        //Then
        Order order = orderRepository.findById(orderId).get();
        assertEquals("주문 완료 시  orderStatus", OrderStatus.ORDER, order.getOrderStatus());
        assertEquals("주문 완료 시 deliveryStatus", DeliveryStatus.PREPARING, order.getDelivery().getDeliveryStatus());
    }

    @Test
    public void 장바구니() throws Exception {
        // Given
        Consumer consumer = createConsumer();
        Long cartId = createCart(consumer);

        // when

    }

    private Consumer createConsumer() {
        Consumer consumer = new Consumer();
        String name= "소비자";
        String cId = "consumer";
        String pwd = "password";
        consumer.setName(name);
        consumer.setCId(cId);
        consumer.setPwd(pwd);
        consumerRepository.save(consumer);
        return consumer;
    }

    private Seller createSeller() {
        Seller seller = new Seller();
        String name= "판매자";
        String sId = "seller";
        String pwd = "password";
        seller.setName(name);
        seller.setSId(sId);
        seller.setPwd(pwd);
        sellerRepository.save(seller);
        return seller;
    }

    private Category createCategory() {
        Category category = new Category();
        categoryRepository.save(category);
        return category;
    }

    private Item createItem(int stockQuantity) {
        Item item = new Item();
        Seller seller = createSeller();
        Category category = createCategory();
        String name= "가방";
        item.setName(name);
        item.setSeller(seller);
        item.setCategory(category);
        item.setStockQuantity(stockQuantity);
        itemRepository.save(item);
        return item;
    }

    private Long createOrder(Consumer consumer) {
        Item item = createItem(20);
        Long orderId = orderService.order(consumer.getId(), item.getId(), 3);
        return orderId;
    }

    private Long createCart(Consumer consumer) {
        Cart cart = Cart.builder()
                .consumer(consumer)
                .build();
        cartRepository.save(cart);
        return cart.getId();
    }

    private void addItemToCart(Long cartId) {
        Item item = createItem(10);


    }
}