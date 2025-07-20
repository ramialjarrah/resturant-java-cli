package io.progressoft.order.model;


public class OrderItem {
    MenuItem item;
    int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public MenuItem getItem() {
        return item;
    }
}
