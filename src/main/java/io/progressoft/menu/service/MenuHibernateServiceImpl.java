package io.progressoft.menu.service;


import io.progressoft.menu.repository.MenuRepository;
import io.progressoft.menu.repository.entities.MenuItemEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuHibernateServiceImpl implements MenuService{

    MenuRepository menuRepository;

    public MenuHibernateServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return mapper(menuRepository.getMenuItem());
    }

    private List<MenuItem> mapper(List<MenuItemEntity> menuItemEntities) {
        List<MenuItem> menuItems = new ArrayList<>();
        for (MenuItemEntity menuItemEntity : menuItemEntities) {
            menuItems.add(new MenuItem(menuItemEntity.getItemUUID(), menuItemEntity.getName(), menuItemEntity.getPrice()));
        }
        return menuItems;
    }

    @Override
    public void addMenuItem(MenuItem menu) {
        menuRepository.insertMenuItem(menu);
    }

    @Override
    public void removeMenuItem(MenuItem menu) {
        menuRepository.removeMenuItem(menu.itemUUID());
    }

    @Override
    public MenuItem findMenuItemById(String id) {
        MenuItemEntity menuItemEntity = menuRepository.findMenuItemById(id);
        if (menuItemEntity == null) {
            throw new IllegalArgumentException("MenuItem with ID " + id + " not found.");
        }
        return new MenuItem(menuItemEntity.getItemUUID(), menuItemEntity.getName(), menuItemEntity.getPrice());
    }
}
