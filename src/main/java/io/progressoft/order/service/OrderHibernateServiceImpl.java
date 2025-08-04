package io.progressoft.order.service;

import io.progressoft.menu.repository.entities.MenuItemEntity;
import io.progressoft.menu.service.MenuItem;
import io.progressoft.order.repository.OrderItemRepository;
import io.progressoft.order.repository.OrderRepository;
import io.progressoft.order.repository.entities.OrderEntity;
import io.progressoft.order.repository.entities.OrderItemEntity;
import io.progressoft.order.service.domain.Order;
import io.progressoft.order.service.domain.OrderItem;
import io.progressoft.order.service.domain.embedded.OrderStatus;
import io.progressoft.order.util.InvoicePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderHibernateServiceImpl implements OrderService{
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;

    public OrderHibernateServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public String createOrder(List<OrderItem> orderItems) {
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId,orderItems,OrderStatus.PREPARING);
        orderRepository.save(order);
        return orderId;
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
        OrderEntity orderEntity = orderRepository.findById(orderId.toString());
        List<OrderItem> orderItems = mapper(orderEntity);

        if (orderEntity == null) throw new RuntimeException("Order not found");
        return new Order(orderEntity.getOrderId(), orderItems, orderEntity.getStatus());
    }

    private static List<OrderItem> mapper(OrderEntity orderEntity) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemEntity orderItemEntity : orderEntity.getItems()) {
            MenuItem menuItem= new MenuItem(orderItemEntity.getMenuItemEntity().getItemUUID(), orderItemEntity.getMenuItemEntity().getName(), orderItemEntity.getMenuItemEntity().getPrice());
            orderItems.add(new OrderItem(menuItem, orderItemEntity.getQuantity()));
        }
        return orderItems;
    }
}
