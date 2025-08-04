package io.progressoft.order.service.domain;


import io.progressoft.menu.service.MenuItem;
import jakarta.persistence.*;

import java.util.UUID;

public class OrderItem {


    private final String uuid;

    private final MenuItem menuItem;

    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.uuid = UUID.randomUUID().toString();
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemId() {
        return uuid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
