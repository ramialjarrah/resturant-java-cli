package io.progressoft.order.repository;

import io.progressoft.order.repository.entities.OrderEntity;
import io.progressoft.order.service.domain.Order;

public interface OrderRepository {
    void save(Order order);

    void update(Order order);

    void removeOrder(String orderId);

    void getOrders();

    OrderEntity findById(String id);
}
