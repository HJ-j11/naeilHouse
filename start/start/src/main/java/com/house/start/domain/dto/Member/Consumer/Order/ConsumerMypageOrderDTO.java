package com.house.start.domain.dto.Member.Consumer.Order;

import com.house.start.domain.Order;
import com.house.start.domain.OrderItem;
import com.house.start.domain.dto.Member.Consumer.OrderItem.ConsumerMypageOrderOrderItemDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ConsumerMypageOrderDTO {
    private String orderDate;
    private List<ConsumerMypageOrderOrderItemDTO> orderItems = new ArrayList<>();

    public ConsumerMypageOrderDTO(Order order) {
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        for (OrderItem orderItem : order.getOrderItems()) {
            this.orderItems.add(new ConsumerMypageOrderOrderItemDTO(orderItem));
        }
    }
}
