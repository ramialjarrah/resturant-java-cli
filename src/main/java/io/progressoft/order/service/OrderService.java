package io.progressoft.order.service;

import io.progressoft.order.model.OrderStatus;

import java.util.List;
import java.util.UUID;


public interface OrderService {

    UUID createOrder(List<Integer> itemNumbers);
    OrderStatus checkOrderStatus(UUID orderId);
    void updateOrderStatus(UUID orderId, OrderStatus newStatus);
    void generateInvoice(UUID orderId);

}
