package io.progressoft.order.repository.entities;

import io.progressoft.order.service.domain.OrderItem;
import io.progressoft.order.service.domain.embedded.OrderStatus;

import java.util.List;
import java.util.UUID;

public record OrderEntity(UUID orderId,List<OrderItem> items,OrderStatus status) {
}
