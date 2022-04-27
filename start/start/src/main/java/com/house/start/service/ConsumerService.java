package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.house.start.domain.Consumer;
import com.house.start.repository.ConsumerRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsumerService {
    private EntityManager em;
    private final PostRepository postRepository;
    private final DeliveryRepository deliveryRepository;
    private final ItemRepository itemRepository;
    private final ConsumerRepository consumerRepository;
    private final OrderRepository orderRepository;

    // 물건 정렬
    public List<Item> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }
    // 카테고리 별 물건 정렬
    public List<Item> getAllByCategory(Long id) {
        List<Item> items = itemRepository.findByCategory(id);
        return items;
    }
    // 물건 상세
    public Item getOneItem(Long id) {
        Item item = itemRepository.getById(id);
        return item;
    }

    // 장바구니 보기
//    public List<Item> findItemByStatus(ItemStatus status) {
//        List<Item> items = itemRepository.findByCart(ItemStatus.CART);
//        return items;
//    }

    // 장바구니 담기
    @Transactional
    public void putCart(Item item) {
        Item Oneitem = em.find(Item.class, item.getId());
        Oneitem.setItemStatus(ItemStatus.CART);

    }

    // 마이페이지
    public Consumer getConsumerInfo(Long id) {
        Consumer user = consumerRepository.getById(id);
        return user;
    }
    // 주문 목록
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    // 배송 목록
    public List<Delivery> getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries;
    }

    // 배송 완료
    @Transactional
    public void completeDelivery(Delivery delivery) {
        Delivery oneDelivery = em.find(Delivery.class, delivery.getId());
        Order order = oneDelivery.getOrder();

        delivery.setDeliveryStatus(DeliveryStatus.COMPLETE);
        order.setOrderStatus(OrderStatus.COMPLETE);
        delivery.setReviewYn(true);

//        em.persist(oneDelivery);
//        em.persist(order);
    }

    // 글 목록
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
    // 글 조회
    public Post getOnePost(Long id) {
        Post post = postRepository.getById(id);
        return post;
    }
    
    // 좋아요
    public void putLikes() {

    }
    
    // 글 작성
    @Transactional
    public void postNew(Post post) {
        postRepository.save(post);
    }

    /**
     * 소비자 전체 목록 조회
     */
    public List<Consumer> findConsumers() {
        return consumerRepository.findAll();
    }
}
