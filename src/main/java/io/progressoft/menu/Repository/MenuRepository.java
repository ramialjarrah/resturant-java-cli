package io.progressoft.menu.Repository;

import io.progressoft.menu.Repository.entities.MenuItemEntity;
import io.progressoft.menu.service.MenuItem;

import java.util.List;

public interface MenuRepository {
    public List<MenuItemEntity> getMenuItems();
    public void addMenuItem(MenuItem menuItem);
    public void removeMenuItem(MenuItem menuItem) ;
    MenuItemEntity findMenuItemById(int menuItemId);
}
