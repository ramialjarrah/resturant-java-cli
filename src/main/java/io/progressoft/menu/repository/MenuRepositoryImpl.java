//package io.progressoft.menu.repository;
//
//import io.progressoft.menu.repository.entities.MenuItemEntity;
//
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MenuRepositoryImpl implements MenuRepository {
//
//    private final Connection conn;
//    public MenuRepositoryImpl(Connection conn) {
//        this.conn = conn;
//    }
//
//    @Override
//    public void insertMenuItem(String UUID, String name, BigDecimal price) {
//        String sql = "INSERT INTO MENU_ITEMS (MENU_ITEMS_ID, NAME, PRICE) VALUES (?, ?, ?)";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, UUID);
//            statement.setString(2, name);
//            statement.setDouble(3, price.doubleValue());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//           throw new RuntimeException("MenuItem not added", e);
//        }
//    }
//
//    @Override
//    public List<MenuItemEntity> getMenuItem() {
//        String sql = "SELECT * FROM MENU_ITEMS";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//            List<MenuItemEntity> menuItems = new ArrayList<>();
//            while (rs.next()) {
//                String uuid = rs.getString("MENU_ITEMS_ID");
//                String name = rs.getString("NAME");
//                double price = rs.getDouble("PRICE");
//                menuItems.add(new MenuItemEntity(uuid, name, BigDecimal.valueOf(price)));
//            }
//            return menuItems;
//        } catch (SQLException e) {
//            throw new RuntimeException("MenuItems not retrieved", e);
//        }
//
//    }
//
//    @Override
//    public void removeMenuItem(String UUID) {
//        String sql = "DELETE FROM MENU_ITEMS WHERE MENU_ITEMS_ID = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, UUID);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("MenuItem not removed", e);
//        }
//    }
//
//    @Override
//    public void updateMenuItem(String UUID, String name, BigDecimal price) {
//        String sql = "UPDATE MENU_ITEMS SET NAME = ?, PRICE = ? WHERE MENU_ITEMS_ID = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, name);
//            statement.setBigDecimal(2, price);
//            statement.setString(3, UUID);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("MenuItem not updated", e);
//        }
//    }
//
//    @Override
//    public MenuItemEntity findMenuItemById(String menuItemId) {
//        String sql = "SELECT * FROM MENU_ITEMS WHERE MENU_ITEMS_ID = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, menuItemId);
//            ResultSet rs = statement.executeQuery();
//
//            if (rs.next()) {
//                String uuid = rs.getString("MENU_ITEMS_ID");
//                String name = rs.getString("NAME");
//                BigDecimal price = rs.getBigDecimal("PRICE");
//                return new MenuItemEntity(uuid, name, price);
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new RuntimeException("MenuItem not found", e);
//        }
//    }
//}
