package io.progressoft.order.service;

import io.progressoft.order.model.MenuItem;
import io.progressoft.order.model.Order;
import io.progressoft.order.model.OrderItem;
import io.progressoft.order.model.OrderStatus;
import io.progressoft.order.repository.OrderRepository;
import io.progressoft.order.util.InvoicePrinter;

import java.util.*;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public UUID createOrder(List<Integer> itemNumbers){

        List<MenuItem> menu = MenuItem.getMenu();

        Map<Integer, Integer> itemCounts = selecetedItemQuantity(itemNumbers);

        List<OrderItem> orderItems = getOrderItems(itemCounts, menu);

        UUID orderId = UUID.randomUUID();
        Order order = new Order(orderId, orderItems, OrderStatus.PREPARING);

        orderRepository.save(order);
        return orderId;
    }

    private static List<OrderItem> getOrderItems(Map<Integer, Integer> itemCounts, List<MenuItem> menu) {
        List<OrderItem> orderItems = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : itemCounts.entrySet()){
            int itemId = entry.getKey();
            int quantity = entry.getValue();

            MenuItem matchedItem = menu.stream()
                    .filter(item -> item.getItemNumber() == itemId)
                    .findFirst()
                    .orElseThrow(()  -> new RuntimeException("Invalid menu item id " + itemId));

        orderItems.add(new OrderItem(matchedItem,quantity));
        } return orderItems;
    }

    private static Map<Integer, Integer> selecetedItemQuantity(List<Integer> itemNumbers) {
        Map<Integer, Integer> itemCounts  = new HashMap<>();
        for(Integer itemNumber : itemNumbers){
            if(itemNumber <= 0) continue;
            itemCounts.put(itemNumber, itemCounts.getOrDefault(itemNumber,0)+1);
        }
        return itemCounts;
    }

    @Override
    public OrderStatus checkOrderStatus(UUID orderId) {


        return  getOrderById(orderId).getStatus();
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
        return orderRepository.findById(orderId).orElseThrow(()  -> new RuntimeException("Invalid order id " + orderId));
    }


}
