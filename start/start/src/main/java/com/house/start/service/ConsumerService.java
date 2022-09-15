package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.domain.entity.Member;
import com.house.start.domain.entity.Role;
import com.house.start.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsumerService {
    private EntityManager em;

    private final PostRepository postRepository;
    private final DeliveryRepository deliveryRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final CartRepository cartRepository;
    private final RoleRepository roleRepository;

    /**
     * 상품
     **/


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
    public Cart findByCart(Member member) {
        Cart cart = cartRepository.findByMember(member);
        return cart;
    }

    // 장바구니 담기
    @Transactional
    public Long addItemToCart(Item item, Cart cart, int count) {
        System.out.println("cart id :  " + cart.getId());

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .count(count)
                .build();

        cart.addCartItem(cartItem);

        cartRepository.save(cart);

//        System.out.print("cartItem id: ");
//        cart.getCartItems().forEach(cartItem1 -> System.out.print(cartItem1.getId()+","));
        List<CartItem> cartItems = cart.getCartItems();

        Long cartId = cartItems.get(cartItems.size()-1).getId();
        return cartId;
    }

    // 마이페이지
    public Member findMemberById(Long id) {
        Member member = memberRepository.findById(id).get();
        return member;
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
        OrderItem orderItem = delivery.getOrderItem();

        Order order = delivery.getOrderItem().getOrder(); // -> 수정

        orderItem.setOrderItemStatus(OrderItemStatus.COMPLETED);
        delivery.setDeliveryStatus(DeliveryStatus.COMPLETE);

        deliveryRepository.save(delivery);
    }

    /**
     * 커뮤니티
     **/

    // 글 목록
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
    // 좋아요 갯수
    public long countByLikes(Long id) {
        return postRepository.countByLikes(id);
    }
    // 글 조회
    public Post getOnePost(Long id) {
        Post post = postRepository.getById(id);
        return post;
    }
    // 글 조회 수 카운트
    @Transactional
    public Long updateView(Long id) {
        return postRepository.updateView(id);
    }

    // 글 좋아요
    @Transactional
    public Long putLikes(Long id, Member member) {
        Post post = postRepository.getById(id);
        Like like = Like.builder()
                .member(member)
                .post(post)
                .build();

        likeRepository.save(like);
        return like.getId();

    }

    // 글 작성
    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }

    /**
     * 댓글
     **/

    // 댓글 등록
    @Transactional
    public void saveComment(String id, String contents, Member member) {
        Post post = getOnePost(Long.valueOf(id));
        Comment comment = Comment.builder()
                .member(member)
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

    /**
     * 소비자 cId로 소비자 조회
     */
    public Member findConsumerBycId(String mId) {
        return memberRepository.findByUsername(mId);
    }

    /*
     * 소비자 전체 목록 조회
     */
    public List<Member> findConsumers() {
        Role role = roleRepository.findByRoleName("ROLE_CONSUMER");
        return memberRepository.findByUserRoles(role);
    }


}
