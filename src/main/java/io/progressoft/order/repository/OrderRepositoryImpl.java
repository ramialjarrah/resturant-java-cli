//package io.progressoft.order.repository;
//
//import io.progressoft.database.DatabaseConnection;
//import io.progressoft.menu.repository.entities.MenuItemEntity;
//import io.progressoft.menu.service.MenuItem;
//import io.progressoft.order.repository.entities.OrderEntity;
//import io.progressoft.order.repository.entities.OrderItemEntity;
//import io.progressoft.order.service.domain.Order;
//import io.progressoft.order.service.domain.OrderItem;
//import io.progressoft.order.service.domain.embedded.OrderStatus;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//
//
//public class OrderRepositoryImpl implements OrderRepository {
//
//    private final Connection conn;
//
//    public OrderRepositoryImpl(Connection conn) {
//
//        this.conn = conn;
//    }
//
//    @Override
//    public void save(Order order) {
//        String sql = "INSERT INTO ORDERS (ORDER_ID, STATUS) VALUES (?,?)";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, order.getOrderId());
//            statement.setString(2, order.getStatus().name());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Order not created", e);
//        }
//
//    }
//
//    @Override
//    public void getOrders() {
//        String sql = "SELECT * FROM ORDERS";
//
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                String orderId = rs.getString("ORDER_ID");
//                String status = rs.getString("STATUS");
//
//                System.out.println("Order ID: " + orderId + ", Status: " + status);
//            }
//
//        } catch (SQLException e) {
//
//            throw new RuntimeException("Orders not retrieved", e);
//        }
//    }
//
//
//    @Override
//    public void removeOrder(String orderId) {
//        String sql = "DELETE FROM ORDERS WHERE ORDER_ID = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, orderId);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//
//            throw new RuntimeException("Order not removed", e);
//        }
//    }
//
//    @Override
//    public void update(Order order) {
//        String sql = "UPDATE ORDERS SET STATUS = ? WHERE ORDER_ID = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, order.getStatus().name());
//            statement.setString(2, order.getOrderId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Order not updated", e);
//        }
//    }
//
//
//    @Override
//    public OrderEntity findById(String orderId) {
//        String sql = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, orderId);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                String uuid = rs.getString("ORDER_ID");
//                OrderStatus status = OrderStatus.valueOf(rs.getString("STATUS"));
//                List<OrderItemEntity> orderItemsEntity = fetchOrderItemsForOrder(orderId);
//                return new OrderEntity(uuid, orderItemsEntity, status);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Order not found", e);
//        }
//        return null;
//    }
//
//    private List<OrderItemEntity> fetchOrderItemsForOrder(String orderId) throws SQLException {
//        String sql = "SELECT * FROM ORDER_ITEMS WHERE ORDERS_REF = ?";
//        PreparedStatement statement = conn.prepareStatement(sql);
//        statement.setString(1, orderId);
//        ResultSet rs = statement.executeQuery();
//
//        List<OrderItemEntity> items = new ArrayList<>();
//
//        while (rs.next()) {
//            String menuItemId = rs.getString("MENU_ITEMS_REF");
//            int quantity = rs.getInt("QUANTITY");
//            String orderItemId = rs.getString("ORDER_ITEMS_ID");
//            MenuItemEntity menuItemEntity = getMenuItemById(menuItemId);
//            items.add(new OrderItemEntity(orderItemId,menuItemEntity, quantity));
//        }
//        return items;
//    }
//
//    private MenuItemEntity getMenuItemById(String menuItemId) throws SQLException {
//        String sql = "SELECT * FROM MENU_ITEMS WHERE MENU_ITEMS_ID = ?";
//        PreparedStatement statement = conn.prepareStatement(sql);
//        statement.setString(1, menuItemId);
//        ResultSet rs = statement.executeQuery();
//        if (rs.next()) {
//            String uuid = rs.getString("MENU_ITEMS_ID");
//            String name = rs.getString("NAME");
//            BigDecimal price = rs.getBigDecimal("PRICE");
//
//            return new MenuItemEntity(uuid, name, price);
//        }
//
//        throw new SQLException("MenuItem not found: " + menuItemId);
//    }
//
//}
