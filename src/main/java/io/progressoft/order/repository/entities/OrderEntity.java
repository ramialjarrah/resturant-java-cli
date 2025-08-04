package io.progressoft.order.repository.entities;

import io.progressoft.order.service.domain.embedded.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @Column(name = "ORDER_ID")
    String orderId;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;


    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    OrderStatus status;


    public OrderEntity(String orderId, List<OrderItemEntity> items, OrderStatus status) {
        this.orderId = orderId;
        this.items = items;
        this.status = status;
    }

    public OrderEntity() {}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}



