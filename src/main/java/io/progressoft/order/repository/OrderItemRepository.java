package io.progressoft.order.repository;

import io.progressoft.order.repository.entities.OrderItemEntity;
import io.progressoft.order.service.domain.Order;
import io.progressoft.order.service.domain.OrderItem;

import java.util.List;

public interface OrderItemRepository {
    void insertOrderItem(OrderItem orderItem);

    List<OrderItemEntity> getOrderItems();

    void updateOrderItem(OrderItem orderItem);

    void removeOrderItem(String orderItemId,String orderID);
}
