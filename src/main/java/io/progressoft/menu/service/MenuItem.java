package io.progressoft.menu.service;

import java.math.BigDecimal;



public record MenuItem (int itemNumber, String name, BigDecimal price){

    @Override
    public String toString() {
        return itemNumber +  ". " + name + " - $" + price;
    }
}
