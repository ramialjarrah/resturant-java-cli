package io.progressoft.order.service;

import io.progressoft.order.service.domain.OrderDomain;

import java.util.List;

public interface OrderService {
    List<OrderDomain> getOrders();
}
