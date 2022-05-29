package com.house.start.domain.dto.Order;

import com.house.start.domain.Order;
import com.house.start.domain.dto.Order.OrderItem.OrderOrderItemAdminDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderAdminDTO {
    private Long id;
    private String cId;
    private LocalDateTime orderDate;
    private List<OrderOrderItemAdminDTO> orderItems;

    @Builder
    public OrderAdminDTO(Order order, List<OrderOrderItemAdminDTO> orderItems) {
        this.id = order.getId();
        this.cId = order.getConsumer().getCId();
        this.orderDate = order.getOrderDate();
        this.orderItems = orderItems;
    }
}
