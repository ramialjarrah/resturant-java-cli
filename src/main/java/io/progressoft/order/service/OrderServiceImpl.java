//package io.progressoft.order.service;
//
//import io.progressoft.order.repository.OrderItemRepository;
//import io.progressoft.order.repository.entities.OrderEntity;
//import io.progressoft.order.service.domain.Order;
//import io.progressoft.order.service.domain.OrderItem;
//import io.progressoft.order.service.domain.embedded.OrderStatus;
//import io.progressoft.order.repository.OrderRepository;
//import io.progressoft.order.util.InvoicePrinter;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.*;
//
//public class OrderServiceImpl implements OrderService {
//
//    private final OrderRepository orderRepository;
//    private final OrderItemRepository orderItemRepository;
//    private final Connection connection;
//
//    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, Connection connection) {
//        this.orderRepository = orderRepository;
//        this.orderItemRepository = orderItemRepository;
//        this.connection = connection;
//    }
//
//    @Override
//    public String createOrder(List<OrderItem> orderItems) {
//        UUID orderId = UUID.randomUUID();
//        Order order = new Order(orderId.toString(), orderItems, OrderStatus.PREPARING);
//        try{
//            connection.setAutoCommit(false);
//            orderRepository.save(order);
//            orderItemRepository.insertOrderItem(orderItems, order);
//            connection.commit();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return orderId.toString();
//    }
//
//    @Override
//    public OrderStatus checkOrderStatus(UUID orderId) {
//
//        return getOrderById(orderId).getStatus();
//    }
//
//    @Override
//    public void updateOrderStatus(UUID orderId, OrderStatus newStatus) {
//        Order order = getOrderById(orderId);
//        order.setStatus(newStatus);
//        orderRepository.update(order);
//        //System.out.println("Order status updated to: " + newStatus);
//    }
//
//    @Override
//    public void generateInvoice(UUID orderId) {
//        InvoicePrinter.print(getOrderById(orderId));
//    }
//
//    private Order getOrderById(UUID orderId) {
//
//        OrderEntity orderEntity = orderRepository.findById(orderId.toString());
//        if (orderEntity == null) throw new RuntimeException("Order not found");
//        return new Order(orderEntity.getOrderId(), null, orderEntity.getStatus());
//    }
//
//}
