package io.progressoft.order.repository.entities;


import io.progressoft.menu.repository.entities.MenuItemEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItemEntity{
        @Id
        String itemId;
        @OneToOne
        @JoinColumn (name = "MENU_ITEM_ID")
        MenuItemEntity menuItemEntity;
        @Column
        int quantity;

        @ManyToOne
        @JoinColumn(name = "ORDER_ID")
        OrderEntity order;

        public OrderItemEntity(String itemId, MenuItemEntity menuItemEntity, int quantity, OrderEntity order) {
                this.itemId = itemId;
                this.menuItemEntity = menuItemEntity;
                this.quantity = quantity;
                this.order = order;
        }

        public OrderItemEntity(String itemId, MenuItemEntity menuItemEntity, int quantity) {
                this.itemId = itemId;
                this.menuItemEntity = menuItemEntity;
                this.quantity = quantity;
        }


        public OrderItemEntity() {}

        public String getItemId() {
                return itemId;
        }

        public void setItemId(String itemId) {
                this.itemId = itemId;
        }

        public MenuItemEntity getMenuItemEntity() {
                return menuItemEntity;
        }

        public void setMenuItemEntity(MenuItemEntity menuItemEntity) {
                this.menuItemEntity = menuItemEntity;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

        public OrderEntity getOrder() {
                return order;
        }

        public void setOrder(OrderEntity order) {
                this.order = order;
        }
}
