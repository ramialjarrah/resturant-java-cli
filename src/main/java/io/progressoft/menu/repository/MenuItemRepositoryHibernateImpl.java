package io.progressoft.menu.repository;

import io.progressoft.menu.repository.entities.MenuItemEntity;
import io.progressoft.menu.service.MenuItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MenuItemRepositoryHibernateImpl implements MenuRepository{

    private final SessionFactory sessionFactory;
    public MenuItemRepositoryHibernateImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MenuItemEntity> getMenuItem() {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<MenuItemEntity> menuItemEntities = session.createQuery("from MenuItemEntity").list();
            session.getTransaction().commit();
            return menuItemEntities;
        } catch (Exception e) {
            throw new RuntimeException("MenuItems not retrieved", e);
        }
    }

    @Override
    public void insertMenuItem(MenuItem menuItem) {
        MenuItemEntity menuItemEntity = new MenuItemEntity(menuItem.itemUUID(), menuItem.name(), menuItem.price());
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(menuItemEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeMenuItem(String UUID) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            MenuItemEntity menuItemEntity = session.find(MenuItemEntity.class, UUID);
            session.remove(menuItemEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateMenuItem(MenuItem menuItem) {
        MenuItemEntity menuItemEntity = new MenuItemEntity(menuItem.itemUUID(), menuItem.name(), menuItem.price());
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.merge(menuItemEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public MenuItemEntity findMenuItemById(String menuItemId) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            MenuItemEntity menuItemEntity = session.find(MenuItemEntity.class, menuItemId);
            session.getTransaction().commit();
            return menuItemEntity;
        } catch (Exception e) {
            throw new RuntimeException("MenuItem not found", e);
        }
    }
}
