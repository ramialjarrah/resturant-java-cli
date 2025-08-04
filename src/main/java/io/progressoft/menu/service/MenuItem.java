package io.progressoft.menu.service;

import java.math.BigDecimal;


public record MenuItem(String itemUUID, String name, BigDecimal price) {

    @Override
    public String toString() {
        return itemUUID + ". " + name + " - $" + price;
    }
}
