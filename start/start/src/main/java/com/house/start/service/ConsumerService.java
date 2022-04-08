package com.house.start.service;

import com.house.start.domain.*;
import com.house.start.repository.DeliveryRepository;
import com.house.start.repository.ItemRepository;
import com.house.start.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ConsumerService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ItemRepository itemRepository;

    // 카테고리별 물건


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


}
