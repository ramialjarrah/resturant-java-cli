package io.progressoft.menu.service;

import java.util.List;

public interface MenuService {
    List<MenuItem> getMenuItems();
    void addMenuItem(MenuItem menu);
    void removeMenuItem(MenuItem menu);
    MenuItem findMenuItemById(int id);
}
