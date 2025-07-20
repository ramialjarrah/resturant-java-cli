package io.progressoft.order.util;

import io.progressoft.order.service.domain.Order;
import io.progressoft.order.service.domain.OrderItem;

public final  class InvoicePrinter {

    private InvoicePrinter() {}

    public static void print(Order order){
        System.out.println("==== INVOICE ====");

        System.out.println("Order ID: " + order.getOrderId());

        for(OrderItem item : order.getItems()){
            System.out.println("- " + item.getMenuItem().name() +" - "+ item.getMenuItem().price() + " - Quantity: " + item.getQuantity());
        }

        System.out.println("Total Amount: " + order.getTotalAmount());

        System.out.println("Order status: " + order.getStatus());

        System.out.println("===================");
    }
}
