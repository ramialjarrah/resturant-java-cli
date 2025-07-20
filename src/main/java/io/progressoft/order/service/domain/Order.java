package io.progressoft.order.service.domain;

import io.progressoft.order.service.domain.embedded.OrderStatus;

import java.math.BigDecimal;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(UUID uuid, List<OrderItem> items, OrderStatus status) {
        this.orderId = uuid;
        this.items = items;
        this.status = status;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = new BigDecimal("0");
        for (OrderItem item : items) {
            totalAmount = totalAmount.add(item.getMenuItem().price().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return totalAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
