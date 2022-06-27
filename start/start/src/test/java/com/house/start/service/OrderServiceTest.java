//package com.house.start.service;
//
//import com.house.start.domain.*;
//import com.house.start.repository.*;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class OrderServiceTest {
//    @Autowired OrderService orderService;
//    @Autowired ConsumerService consumerService;
//
//    @Autowired OrderRepository orderRepository;
//    @Autowired ConsumerRepository consumerRepository;
//    @Autowired SellerRepository sellerRepository;
//    @Autowired ItemRepository itemRepository;
//    @Autowired CategoryRepository categoryRepository;
//    @Autowired CartRepository cartRepository;
//    @Autowired OrderItemRepository orderItemRepository;
//    @Autowired DeliveryRepository deliveryRepository;
//
//    @Test
//    public void 주문취소() throws Exception {
//        //Given -> 나중에 order 부분 수정 후 그 부분으로 바꿀 예정
//        Consumer consumer = createConsumer();
//        Item item = createItem(10);
//        int orderCount = 2;
//        Delivery delivery = createDelivery();
//        OrderItem orderItem = createOrderItem(item, delivery, orderCount);
//
//        //When
//        Long orderItemId = orderItem.getId();
//        orderService.cancelOrder(orderItemId);
//
//        //Then
//        OrderItem testOrderItem = orderItemRepository.findById(orderItemId).get();
//        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderItemStatus.CANCELED, testOrderItem.getOrderItemStatus());
//        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
//    }
//
//    @Test
//    public void 주문완료() throws Exception {
//        //Given
//        Consumer consumer = createConsumer();
//        Item item = createItem(100);
//        int count = 2;
//        // when
//
//        Long orderId = orderService.order(consumer.getId(), item.getId(), count);
//
//        //Then
//        Order order = orderRepository.findById(orderId).get();
//        List<OrderItem> orderItems = order.getOrderItems();
//        for(OrderItem orderItem : orderItems) {
//            assertEquals("orderItem Status", OrderItemStatus.COMPLETED, orderItem.getOrderItemStatus());
//            assertEquals("Delivery Status", DeliveryStatus.PREPARING, orderItem.getDelivery().getDeliveryStatus());
//        }
//
//
//    }
//
//    @Test
//    public void 장바구니() throws Exception {
//        // Given
//        Consumer consumer = createConsumer();
//
//        // when
//        Long cartId = createCart(consumer);
//
//        //Then
//        Cart cart = cartRepository.getById(cartId);
//
//        assertEquals("장바구니 객체 확인", consumer.getId(), cart.getConsumer().getId());
//    }
//    @Test
//    public void 장바구니상품생성() {
//        // Given
//        Consumer consumer = createConsumer();
//        Item itm = createItem(10);
//        Cart cart = cartRepository.getById(createCart(consumer));
//
//        //When
//        int count = 2;
//        Long cartItemId = consumerService.addItemToCart(itm, cart, 2);
//        CartItem cartItem = cartRepository.getById(cartItemId).getCartItems().get(0);
//        //Then
//        assertEquals("상품이 생겼는지", itm.getId(), cartItem.getItem().getId());
//    }
//    @Test
//    public void 장바구니물건담기() {
//        //Given
//        Consumer consumer = createConsumer();
//        Cart cart = cartRepository.getById(createCart(consumer));
//        Item item = createItem(100);
//        int count = 2;
//        //when
//        List<CartItem> cartItems = createCartItem(cart, item, count);
//
//        // Then
//        assertEquals("item to Cart", item.getId(), cartItems.get(0).getItem().getId());
//    }
//
//    @Test
//    public void 장바구니물건구매() {
//        //Given
//        Consumer consumer = createConsumer();
//        Cart cart = cartRepository.getById(createCart(consumer));
//
//        //When
//        List<Item> items = makeItem("소파", "의자");
//        for (Item item: items) {
//            createCartItem(cart, item, 2);
//        }
//
//        Long orderId = orderService.orders(consumer);
//
//        //Then
//        Order order = orderRepository.getById(orderId);
//
//        for (int i = 0; i < order.getOrderItems().size(); i++) {
//            Item cartInItem = cart.getCartItems().get(i).getItem();
//            OrderItem orderInItem = order.getOrderItems().get(i);
//            assertEquals("장바구니 구매 상품 확인", cartInItem.getName(), orderInItem.getItem().getName());
//            assertEquals("상품 status 확인", OrderItemStatus.COMPLETED, orderInItem.getOrderItemStatus());
//            assertEquals("Delivery status", DeliveryStatus.PREPARING, orderInItem.getDelivery().getDeliveryStatus());
//        }
//
//    }
//
//
//    private Consumer createConsumer() {
//        Consumer consumer = new Consumer();
//        String name= "소비자";
//        String cId = "consumer";
//        String pwd = "password";
//        consumer.setName(name);
//        consumer.setCId(cId);
//        consumer.setPwd(pwd);
//        consumerRepository.save(consumer);
//        return consumer;
//    }
//
//    private Seller createSeller() {
//        Seller seller = new Seller();
//        String name= "판매자";
//        String sId = "seller";
//        String pwd = "password";
//        seller.setName(name);
//        seller.setSId(sId);
//        seller.setPwd(pwd);
//        sellerRepository.save(seller);
//        return seller;
//    }
//
//    private Category createCategory() {
//        Category category = new Category();
//        categoryRepository.save(category);
//        return category;
//    }
//
//    private Item createItem(int stockQuantity) {
//        Item item = new Item();
//        Seller seller = createSeller();
//        Category category = createCategory();
//        String name= "가방";
//        item.setName(name);
//        item.setSeller(seller);
//        item.setCategory(category);
//        item.setStockQuantity(stockQuantity);
//        itemRepository.save(item);
//        return item;
//    }
//    private List<Item> makeItem(String name1, String name2) {
//        List<Item> items = new ArrayList<>();
//        Seller seller = createSeller();
//        Item item1 = Item.builder()
//                .name(name1)
//                .seller(seller)
//                .price(1000)
//                .build();
//        itemRepository.save(item1);
//        items.add(item1);
//        Item item2 = Item.builder()
//                .name(name2)
//                .seller(seller)
//                .price(2000)
//                .build();
//        itemRepository.save(item2);
//        items.add(item2);
//
//        return items;
//    }
//    private Long createOrder(Consumer consumer) {
//        Item item = createItem(20);
//        Long orderId = orderService.order(consumer.getId(), item.getId(), 3);
//        return orderId;
//    }
//
//    private Long createCart(Consumer consumer) {
//        Cart cart = Cart.builder()
//                .consumer(consumer)
//                .build();
//        cartRepository.save(cart);
//        return cart.getId();
//    }
//
//    private List<CartItem> createCartItem(Cart cart, Item item, int count) {
//        CartItem cartItem = CartItem.builder()
//                .item(item)
//                .cart(cart)
//                .count(count)
//                .build();
//        cart.addCartItem(cartItem);
//        return cart.getCartItems();
//    }
//
//    private OrderItem createOrderItem(Item item, Delivery delivery, int ordercount) {
//        OrderItem orderItem = OrderItem.builder()
//                .item(item)
//                .delivery(delivery)
//                .count(ordercount)
//                .orderItemStatus(OrderItemStatus.ORDER)
//                .build();
//        orderItemRepository.save(orderItem);
//        return orderItem;
//    }
//
//    private Delivery createDelivery() {
//        Delivery delivery = Delivery.builder()
//                .deliveryStatus(DeliveryStatus.DELIVERING)
//                .build();
//        deliveryRepository.save(delivery);
//        return delivery;
//    }
//}