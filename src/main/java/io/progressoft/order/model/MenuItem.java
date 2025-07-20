package io.progressoft.order.model;

import java.math.BigDecimal;
import java.util.List;



public class MenuItem {
    String name;
    BigDecimal price;
    int itemNumber;

    public MenuItem(int itemNumber, String name, BigDecimal price) {
        this.itemNumber = itemNumber;
        this.name = name;
        this.price = price;
    }

    public static List<MenuItem> getMenu(){

        return  List.of(
                new MenuItem(1, "Burger", new BigDecimal("5.99")),
                new MenuItem(2, "Pizza", new BigDecimal("7.99")),
                new MenuItem(3, "Salad", new BigDecimal("4.99"))
        );
    }

    public String getName() {
        return name;
    }
    public int getItemNumber() {
        return itemNumber;
    }
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return itemNumber +  ". " + name + " - $" + price;
    }
}
