package io.progressoft.menu.Repository;

import io.progressoft.menu.Repository.entities.MenuItemEntity;
import io.progressoft.menu.service.MenuItem;

import java.math.BigDecimal;
import java.util.List;

public class MenuRepositoryImpl implements MenuRepository {
    List<MenuItemEntity> menuItems;
    public MenuRepositoryImpl() {
        this.menuItems = List.of(
                new MenuItemEntity(1, "Burger", new BigDecimal("5.99")),
                new MenuItemEntity(2, "Pizza", new BigDecimal("7.99")),
                new MenuItemEntity(3, "Salad", new BigDecimal("4.99"))
        );
    }

    public List<MenuItemEntity> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        MenuItemEntity menuItemEntity = new MenuItemEntity(menuItem.itemNumber(), menuItem.name(), menuItem.price());
        menuItems.add(menuItemEntity);
    }

    public void removeMenuItem(MenuItem menuItem) {
        MenuItemEntity menuItemEntity = new MenuItemEntity(menuItem.itemNumber(), menuItem.name(), menuItem.price());
        menuItems.remove(menuItemEntity);
    }

    @Override
    public MenuItemEntity findMenuItemById(int menuItemId) {
        for(MenuItemEntity menuItemEntity : menuItems) {
            if(menuItemEntity.itemNumber() == menuItemId) {
                return menuItemEntity;
            }
        }
        return null;
    }
}
