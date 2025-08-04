//package io.progressoft.menu.service;
//
//import io.progressoft.menu.repository.MenuRepository;
//import io.progressoft.menu.repository.entities.MenuItemEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MenuServiceImpl implements MenuService {
//
//    MenuRepository menuRepository;
//
//    public MenuServiceImpl(MenuRepository menuRepository) {
//        this.menuRepository = menuRepository;
//    }
//
//    @Override
//    public List<MenuItem> getMenuItems() {
//        return convertItems();
//    }
//
//    @Override
//    public void addMenuItem(MenuItem menu) {
//        menuRepository.insertMenuItem(menu.itemUUID(), menu.name(), menu.price());
//          System.out.println("item added");
//    }
//
//    @Override
//    public void removeMenuItem(MenuItem menu) {
//        menuRepository.removeMenuItem(menu.itemUUID());
//        System.out.println("item removed");
//    }
//
//    @Override
//    public MenuItem findMenuItemById(String id) {
//
//        MenuItemEntity menuItemEntity = menuRepository.findMenuItemById(id);
//        if (menuItemEntity != null)
//            return new MenuItem(menuItemEntity.getItemUUID(), menuItemEntity.getName(), menuItemEntity.getPrice());
//        else
//            throw new RuntimeException("item not found");
//    }
//
//    private List<MenuItem> convertItems() {
//        List<MenuItem> menuItems = new ArrayList<>();
//        List<MenuItemEntity> menuItemEntities = menuRepository.getMenuItem();
//        for (MenuItemEntity menuItemEntity : menuItemEntities) {
//            menuItems.add(new MenuItem(menuItemEntity.getItemUUID(), menuItemEntity.getName(), menuItemEntity.getPrice()));
//        }
//        return menuItems;
//    }
//}
