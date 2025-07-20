package io.progressoft.menu.service;

import io.progressoft.menu.Repository.MenuRepository;
import io.progressoft.menu.Repository.entities.MenuItemEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuServiceImpl implements MenuService {

    MenuRepository menuRepository;
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return convertItems();
    }

    @Override
    public void addMenuItem(MenuItem menu) {
        menuRepository.addMenuItem(menu);
    }

    @Override
    public void removeMenuItem(MenuItem menu) {
        menuRepository.removeMenuItem(menu);
    }

    @Override
    public MenuItem findMenuItemById(int id) {
        List<MenuItem> menuItems = convertItems();
        for(MenuItem menuItem: menuItems) {
            if(menuItem.itemNumber() == id) {
                return menuItem;
            }
        }
       throw new RuntimeException("item not found");
    }

    private List<MenuItem> convertItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        List<MenuItemEntity> menuItemEntities = menuRepository.getMenuItems();
        for(MenuItemEntity menuItemEntity: menuItemEntities) {
            menuItems.add(new MenuItem(menuItemEntity.itemNumber(),menuItemEntity.name(),menuItemEntity.price()));
        }
        return menuItems;
    }
}
