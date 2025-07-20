package io.progressoft.order.repository;

import io.progressoft.order.model.Order;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepositoryImpl implements OrderRepository {
    
    private final Map<UUID, Order> orders = new ConcurrentHashMap<>();


    @Override
    public void save(Order order) {
        if(orders.containsKey(order.getOrderId())){

            throw new RuntimeException("Order already exists");
        }

        orders.put(order.getOrderId(),order);
    }

    @Override
    public void update(Order order) {

        if(!orders.containsKey(order.getOrderId())) {
            throw new RuntimeException("Order id not found");
        }

        orders.put(order.getOrderId(),order);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        for(Order order : orders.values()){
            if(order.getOrderId().equals(id)){
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

}
