package com.house.start.domain.dto;

import com.house.start.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private Long orderItemCnt;
    private List<OrderItemDTO> orderItems;

    @Builder
    public OrderDTO(Order order, Long orderItemCnt, List<OrderItemDTO> orderItems) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.orderItemCnt = orderItemCnt;

        this.orderItems = orderItems;
    }
}
