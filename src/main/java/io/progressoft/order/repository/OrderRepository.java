package io.progressoft.order.repository;

import io.progressoft.order.service.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(UUID id);
    void update(Order order);
}
