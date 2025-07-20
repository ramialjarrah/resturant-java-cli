package io.progressoft.order.service;

import io.progressoft.order.service.domain.OrderItem;
import io.progressoft.order.service.domain.embedded.OrderStatus;

import java.util.List;
import java.util.UUID;


public interface OrderService {

    String createOrder(List<OrderItem> orderItems);
    OrderStatus checkOrderStatus(UUID orderId);
    void updateOrderStatus(UUID orderId, OrderStatus newStatus);
    void generateInvoice(UUID orderId);

}
