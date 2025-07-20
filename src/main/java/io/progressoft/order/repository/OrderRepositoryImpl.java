package io.progressoft.order.repository;

import io.progressoft.order.repository.entities.OrderEntity;
import io.progressoft.order.service.domain.Order;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepositoryImpl implements OrderRepository {

    private final Map<UUID, OrderEntity> orders = new ConcurrentHashMap<>();

    @Override
    public void save(Order order) {
        if (order.getOrderId() != null && orders.containsKey(order.getOrderId())) {
            throw new RuntimeException("Order already exists");
        }

        OrderEntity orderEntity = new OrderEntity(order.getOrderId(),order.getItems(),order.getStatus());
        orders.put(orderEntity.orderId(), orderEntity);
    }

    @Override
    public void update(Order order) {
        if (!orders.containsKey(order.getOrderId())) {
            throw new RuntimeException("Order id not found");
        }

        OrderEntity orderEntity = new OrderEntity(order.getOrderId(),order.getItems(),order.getStatus());
        orders.put(order.getOrderId(), orderEntity);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        for (OrderEntity orderEntity : orders.values()) {
            if (orderEntity.orderId().equals(id)) {
                return Optional.of(new Order(orderEntity.orderId(), orderEntity.items(), orderEntity.status()));
            }
        }
        return Optional.empty();
    }


}
