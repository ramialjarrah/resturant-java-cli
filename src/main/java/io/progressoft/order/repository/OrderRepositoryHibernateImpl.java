package io.progressoft.order.repository;

import io.progressoft.menu.repository.entities.MenuItemEntity;
import io.progressoft.order.repository.entities.OrderEntity;
import io.progressoft.order.repository.entities.OrderItemEntity;
import io.progressoft.order.service.domain.Order;
import io.progressoft.order.service.domain.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryHibernateImpl implements OrderRepository {

    private final SessionFactory sessionFactory;

    public OrderRepositoryHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Order order) {

        try (Session session = sessionFactory.openSession()) {
            OrderEntity orderEntity = getOrderEntity(order, session);
            session.beginTransaction();
            session.persist(orderEntity);
            session.getTransaction().commit();
        }
    }

    private OrderEntity getOrderEntity(Order order, Session session) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setStatus(order.getStatus());

        for (OrderItem item : order.getItems()) {

            MenuItemEntity menuItemEntity = session.find(MenuItemEntity.class, item.getMenuItem().itemUUID());

            if (menuItemEntity == null) {
                throw new IllegalArgumentException("MenuItem with ID " + item.getMenuItem().itemUUID() + " not found.");
            }

            OrderItemEntity oie = new OrderItemEntity(
                    item.getItemId(),
                    menuItemEntity,
                    item.getQuantity()
            );
            oie.setOrder(orderEntity);

            orderItemEntities.add(oie);
        }

        orderEntity.setItems(orderItemEntities);
        return orderEntity;
    }

    @Override
    public void update(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrderEntity orderEntity = getOrderEntity(order, session);
            session.merge(orderEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeOrder(String orderId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrderEntity orderEntity = session.find(OrderEntity.class, orderId);
            session.remove(orderEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void getOrders() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<OrderEntity> orderEntities = session.createQuery("from OrderEntity").list();
            for (OrderEntity orderEntity : orderEntities) {
                System.out.println("Order ID: " + orderEntity.getOrderId() + ", Status: " + orderEntity.getStatus());
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public OrderEntity findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrderEntity orderEntity = session.find(OrderEntity.class, id);
            session.getTransaction().commit();
            return orderEntity;
        } catch (Exception e) {
            throw new RuntimeException("Order not found", e);
        }
    }
}
