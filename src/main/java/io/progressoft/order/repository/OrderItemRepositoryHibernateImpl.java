package io.progressoft.order.repository;

import io.progressoft.menu.repository.entities.MenuItemEntity;
import io.progressoft.order.repository.entities.OrderEntity;
import io.progressoft.order.repository.entities.OrderItemEntity;
import io.progressoft.order.service.domain.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderItemRepositoryHibernateImpl implements OrderItemRepository{
    SessionFactory sessionFactory;
    public OrderItemRepositoryHibernateImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertOrderItem(OrderItem orderItem) {
        try(Session session = sessionFactory.openSession()){
            MenuItemEntity menuItemEntity = session.find(MenuItemEntity.class, orderItem.getMenuItem().itemUUID());
            OrderItemEntity orderItemEntity = new OrderItemEntity(orderItem.getItemId(),menuItemEntity,orderItem.getQuantity());
            session.beginTransaction();
            session.persist(orderItemEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<OrderItemEntity> getOrderItems() {

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<OrderItemEntity> orderItemEntities = session.createQuery("from OrderItemEntity").list();
            session.getTransaction().commit();
            return orderItemEntities;
        } catch (Exception e){
            throw new RuntimeException("OrderItems not retrieved", e);
        }

    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            OrderItemEntity orderItemEntity = session.find(OrderItemEntity.class, orderItem.getItemId());
            session.merge(orderItemEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeOrderItem(String orderItemId,String orderID) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            OrderItemEntity orderItemEntity = session.find(OrderItemEntity.class, orderItemId);
            OrderEntity orderEntity = session.find(OrderEntity.class, orderID);
            orderEntity.getItems().remove(orderItemEntity);
            session.remove(orderItemEntity);
            session.getTransaction().commit();
        }
    }

}
