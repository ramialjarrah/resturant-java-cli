//package io.progressoft.order.repository;
//
//import io.progressoft.database.DatabaseConnection;
//import io.progressoft.order.service.domain.Order;
//import io.progressoft.order.service.domain.OrderItem;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.List;
//
//public class OrderItemRepositoryImpl implements OrderItemRepository {
//
//    private final Connection conn;
//    public OrderItemRepositoryImpl(Connection conn) {
//        this.conn = conn;
//    }
//
//    @Override
//    public void insertOrderItem(List<OrderItem> orderItems, Order order) {
//        String sql = "INSERT INTO ORDER_ITEMS (ORDER_ITEMS_ID, QUANTITY, ORDERS_REF, MENU_ITEMS_REF) VALUES (?,?,?,?)";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//
//            for(OrderItem item : order.getItems()) {
//                statement.setString(1,item.getItemId());
//                statement.setInt(2,item.getQuantity());
//                statement.setString(3,order.getOrderId());
//                statement.setString(4,item.getMenuItem().itemUUID());
//                statement.addBatch();
//            }
//            statement.executeBatch();
//        } catch (Exception e) {
//            throw new RuntimeException("OrderItem not added", e);
//        }
//    }
//
//    @Override
//    public void getOrderItems() {
//        String sql = "SELECT * FROM ORDER_ITEMS";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                String orderItemId = rs.getString("ORDER_ITEMS_ID");
//                int quantity = rs.getInt("QUANTITY");
//                String orderId = rs.getString("ORDERS_REF");
//                String itemId = rs.getString("MENU_ITEMS_REF");
//
//                System.out.println(orderItemId + " | " + quantity + " | " + orderId + " | " + itemId);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("OrderItems not retrieved", e);
//        }
//    }
//
//
//    @Override
//    public void updateOrderItem(String orderItemId, int quantity, String orderId, String itemId) {
//        String sql = "UPDATE ORDER_ITEMS SET QUANTITY = ? WHERE ORDER_ITEMS_ID = ? AND MENU_ITEMS_REF = ? AND ORDERS_REF = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setInt(1, quantity);
//            statement.setString(2, orderItemId);
//            statement.setString(3, itemId);
//            statement.setString(4, orderId);
//            statement.executeUpdate();
//        } catch (Exception e) {
//            throw new RuntimeException("OrderItem not updated", e);
//        }
//    }
//
//    @Override
//    public void removeOrderItem(String orderItemId, String orderID, String itemID) {
//
//        String sql = "DELETE FROM ORDER_ITEMS WHERE ORDER_ITEMS_ID = ? AND MENU_ITEMS_REF = ? AND ORDERS_REF = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, orderItemId);
//            statement.setString(2, itemID);
//            statement.setString(3, orderID);
//            statement.executeUpdate();
//        } catch (Exception e) {
//            throw new RuntimeException("OrderItem not removed", e);
//        }
//    }
//}
