package io.progressoft.order.service.domain;


import io.progressoft.menu.service.MenuItem;

public class OrderItem {

    private final MenuItem menuItem;
    private int quantity = 0;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
