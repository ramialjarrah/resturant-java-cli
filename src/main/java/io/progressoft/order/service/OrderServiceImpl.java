package io.progressoft.order.service;

import io.progressoft.order.repository.entities.OrderEntity;
import io.progressoft.order.service.domain.Order;
import io.progressoft.order.service.domain.OrderItem;
import io.progressoft.order.service.domain.embedded.OrderStatus;
import io.progressoft.order.repository.OrderRepository;
import io.progressoft.order.util.InvoicePrinter;

import java.util.*;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String createOrder(List<OrderItem> orderItems) {
        UUID orderId = UUID.randomUUID();
        Order order = new Order(orderId, orderItems, OrderStatus.PREPARING);
        orderRepository.save(order);
        return orderId.toString();
    }

    @Override
    public OrderStatus checkOrderStatus(UUID orderId) {
        return getOrderById(orderId).getStatus();
    }

    @Override
    public void updateOrderStatus(UUID orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        order.setStatus(newStatus);
        orderRepository.update(order);
    }

    @Override
    public void generateInvoice(UUID orderId) {
        InvoicePrinter.print(getOrderById(orderId));
    }

    private Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Invalid order id " + orderId));
    }

//    public UUID getOrderId(){
//        return
//    }


}
