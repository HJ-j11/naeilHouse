package com.house.start.service;

import com.house.start.domain.Consumer;
import com.house.start.domain.Item;
import com.house.start.domain.OrderItem;
import com.house.start.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    /**
     * orderitem_id로 Item 조회
     */
    public OrderItem findOrderItem(Long orderitem_id) {
        OrderItem orderItem = orderItemRepository.findById(orderitem_id)
                .orElse(null);
        return orderItem;



    }
}
