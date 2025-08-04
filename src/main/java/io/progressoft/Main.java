package io.progressoft;

import io.progressoft.menu.repository.MenuItemRepositoryHibernateImpl;
import io.progressoft.menu.repository.MenuRepository;

import io.progressoft.menu.service.MenuHibernateServiceImpl;
import io.progressoft.menu.service.MenuItem;

import io.progressoft.menu.service.MenuService;
import io.progressoft.order.repository.*;


import io.progressoft.order.service.OrderHibernateServiceImpl;
import io.progressoft.order.service.OrderService;
import io.progressoft.order.service.domain.OrderItem;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class Main {
    private final Scanner input;
    private final MenuService menuService;
    private final OrderService orderService;

    public Main() {

        Configuration config = new Configuration();
        config.configure();

        SessionFactory sessionFactory = config.buildSessionFactory();
        MenuRepository menuRepository = new MenuItemRepositoryHibernateImpl(sessionFactory);
        OrderRepository orderRepository = new OrderRepositoryHibernateImpl(sessionFactory);
        OrderItemRepository orderItemRepository = new OrderItemRepositoryHibernateImpl(sessionFactory);
        this.menuService = new MenuHibernateServiceImpl(menuRepository);
        this.orderService = new OrderHibernateServiceImpl(orderRepository, orderItemRepository);
        this.input = new Scanner(System.in);
    }


    public static void main(String[] args) {
        Main main = new Main();

        main.start();
    }

    private void start() {
        System.out.println("Welcome to the Restaurant App!");
        while (true) {
            System.out.println("1. Choose Items");
            System.out.println("2. Display Order Status");
            System.out.println("3. Display Invoice");
            System.out.println("0. Exit");
            int choice = input.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
                case 1:
                    chooseItems();
                    break;
                case 2:
                    displayOrderStatus();
                    break;
                case 3:
                    displayInvoice();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }



    private void displayInvoice() {
        System.out.print("Enter Order ID: ");
        UUID orderId = UUID.fromString(input.next());
        orderService.generateInvoice(orderId);
    }

    private void displayOrderStatus() {
        System.out.print("Enter Order Id: ");
        UUID orderId = UUID.fromString(input.next());
        System.out.println("Order Status: " + orderService.checkOrderStatus(orderId));
    }

    private void chooseItems() {

        Map<Integer, MenuItem> menuItems = showMenu();
        List<OrderItem> chosenItems = new ArrayList<>();
        int choice;
        do {
            System.out.println("Enter the Item you want to add (0 to finish): ");
            choice = input.nextInt();
            if (choice == 0)
                break;
            MenuItem menuItem = menuService.findMenuItemById(menuItems.get(choice).itemUUID());

            boolean added = false;
            for (OrderItem orderItem : chosenItems) {
                if (orderItem.getMenuItem().equals(menuItem)) {
                    orderItem.setQuantity(orderItem.getQuantity() + 1);
                    added = true;
                }
            }

            if (!added) {
                chosenItems.add(new OrderItem(menuItem, 1));
            }

        } while (true);

        String uuid = orderService.createOrder(chosenItems);

        System.out.println("Order created: " + uuid);
    }

    private Map<Integer, MenuItem> showMenu() {
        MenuItem menuItem1 = new MenuItem("lkjlds;jjfdslfs1", "pizza", new BigDecimal("2.5"));
        MenuItem menuItem2 = new MenuItem("lkjlds;jjfdslfs2", "burger", new BigDecimal("3.5"));
        MenuItem menuItem3 = new MenuItem("lkjlds;jjfdslfs3", "chips", new BigDecimal("1.5"));
        menuService.addMenuItem(menuItem1);
        menuService.addMenuItem(menuItem2);
        menuService.addMenuItem(menuItem3);

        List<MenuItem> menuItems = menuService.getMenuItems();
        Map<Integer, MenuItem> menuItemMap = new HashMap<>();
        int i = 1;
        for (MenuItem menuItem : menuItems) {
            menuItemMap.put(i, menuItem);
            i++;
        }
        System.out.println("Menu:");
        for (Map.Entry<Integer, MenuItem> entry : menuItemMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().name() + " - " + entry.getValue().price());
        }
        return menuItemMap;
    }
}