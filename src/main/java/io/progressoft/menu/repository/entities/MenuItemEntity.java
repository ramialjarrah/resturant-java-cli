package io.progressoft.menu.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "MENU_ITEM")
public class MenuItemEntity{

    @Id
    @Column(name = "ITEM_UUID") // or your actual column name
    String itemUUID;

    @Column(name = "ITEM_NAME") String name;
        @Column BigDecimal price;

    public MenuItemEntity(String itemUUID, String name, BigDecimal price) {
        this.itemUUID = itemUUID;
        this.name = name;
        this.price = price;
    }

    public MenuItemEntity() {

    }

    public String getItemUUID() {
        return itemUUID;
    }

    public void setItemUUID(String itemUUID) {
        this.itemUUID = itemUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}