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
     * id로 OrderItem 조회
     */
    public OrderItem findOrderItemById(Long orderitem_id) {
        return orderItemRepository.findById(orderitem_id)
                .orElse(null);
    }
}
