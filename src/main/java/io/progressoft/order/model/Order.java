package io.progressoft.order.model;

import java.math.BigDecimal;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID ORDER_ID;
    List<OrderItem> items;
    OrderStatus status;

    public Order(UUID orderId, List<OrderItem> items, OrderStatus status) {
        this.ORDER_ID = orderId;
        this.items = items;
        this .status = status;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public BigDecimal getTotalAmount(){
        BigDecimal totalAmount = new BigDecimal("0");
        for(OrderItem item : items){
            totalAmount = totalAmount.add(item.item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return totalAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public UUID getOrderId() {
        return ORDER_ID;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
