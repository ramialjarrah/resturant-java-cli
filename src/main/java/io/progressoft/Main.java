package io.progressoft;

import io.progressoft.menu.Repository.MenuRepository;
import io.progressoft.menu.Repository.MenuRepositoryImpl;
import io.progressoft.menu.service.MenuItem;
import io.progressoft.menu.service.MenuService;
import io.progressoft.menu.service.MenuServiceImpl;
import io.progressoft.order.repository.OrderRepository;
import io.progressoft.order.repository.OrderRepositoryImpl;
import io.progressoft.order.service.OrderService;
import io.progressoft.order.service.OrderServiceImpl;
import io.progressoft.order.service.domain.OrderItem;


import java.util.*;

import static org.apache.commons.lang3.StringUtils.isBlank;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    OrderRepository orderRepository = new OrderRepositoryImpl();
    OrderService orderService = new OrderServiceImpl(orderRepository);

    MenuRepository menuRepository = new MenuRepositoryImpl();
    MenuService menuService = new MenuServiceImpl(menuRepository);

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
//        main.start();

        String x = "Hi there";
        if (isBlank(x)) {

        }
    }

    void start() {


        while (true) {
            System.out.println(" Restaurant CLI System\n" +
                    "1. Create Order\n" +
                    "2. Check Order Status\n" +
                    "3. Print Invoice\n" +
                    "4. Exit");

            System.out.print("Choose: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    showMenu();
                    chooseItems();
                    break;
                case 2:
                    displayOrderStatus();
                    break;
                case 3:
                    displayInvoice();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choice");
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
        List<OrderItem> chosenItems = new ArrayList<OrderItem>();
        int choice;
        do {
            System.out.println("Enter the Item you want to add (0 to finish): ");
            choice = input.nextInt();
            if(choice == 0)
                break;
            MenuItem menuItem = menuService.findMenuItemById(choice);

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

    private void showMenu() {

        List<MenuItem> menuItems = menuService.getMenuItems();
        System.out.println("Menu:");
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem);
        }

    }
}