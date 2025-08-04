package io.progressoft.menu.repository;

import io.progressoft.menu.repository.entities.MenuItemEntity;
import io.progressoft.menu.service.MenuItem;

import java.math.BigDecimal;
import java.util.List;

public interface MenuRepository {
    List<MenuItemEntity> getMenuItem();

    void insertMenuItem(MenuItem menuItem);

    void removeMenuItem(String UUID);

    void updateMenuItem(MenuItem menuItem);

    MenuItemEntity findMenuItemById(String menuItemId);
}
