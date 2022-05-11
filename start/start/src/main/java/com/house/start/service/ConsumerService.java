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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    /**
     * 상품
     * **/


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

//    // 장바구니 보기
//    public List<Item> findByCart(ItemStatus status) {
//        List<Item> items = itemRepository.findByCart(ItemStatus.CART);
//        return items;
//    }

    // 장바구니 담기
    @Transactional
    public void goToCart(Long id) {
        Item item = itemRepository.getById(id);
        item.setItemStatus(ItemStatus.CART);
        itemRepository.save(item);
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
    public void completeDelivery(Long id) {
        Delivery delivery = deliveryRepository.getById(id);
        Order order = delivery.getOrder();

        delivery.setDeliveryStatus(DeliveryStatus.COMPLETE);
        order.setOrderStatus(OrderStatus.COMPLETE);
        delivery.setReviewYn(true);

        deliveryRepository.save(delivery);

    }

    /**
     * 커뮤니티
     * **/
    
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

    // 글 좋아요
    @Transactional
    public void putLikes(Long id) {
        Post post = postRepository.getById(id);
        // session 구현되면 consumer 넣기
        Like like = Like.builder()
                .consumer(post.getConsumer())
                .post(post)
                .build();

        likeRepository.save(like);
    }
    
    // 글 작성
    @Transactional
    public void save(Post post) {
        postRepository.save(post);
    }

    /**
     * 댓글
     * **/
    
    // 댓글 등록
    @Transactional
    public void saveComment(String id, String contents) {
        Post post = getOnePost(Long.valueOf(id));
        Comment comment = Comment.builder()
                .consumer(post.getConsumer())
                .post(post)
                .content(contents)
                .build();
        commentRepository.save(comment);
    }
    
    // 댓글 수정
    @Transactional
    public void updateComment(Long id, String content) {
        Comment comment = commentRepository.getById(id);
        comment.setContent(content);

        commentRepository.save(comment);

    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.getById(id);
        commentRepository.delete(comment);
    }
    /*
     * 소비자 전체 목록 조회
     */
    public List<Consumer> findConsumers() {
        return consumerRepository.findAll();
    }

    /**
     * 소비자 cId로 소비자 조회
     */
    public Consumer findConsumerBycId(String cId) {
        return consumerRepository.findBycId(cId)
                .orElse(null);
    }

}
