package io.progressoft;

import io.progressoft.order.model.MenuItem;
import io.progressoft.order.repository.OrderRepository;
import io.progressoft.order.repository.OrderRepositoryImpl;
import io.progressoft.order.service.OrderService;
import io.progressoft.order.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    OrderRepository orderRepository = new OrderRepositoryImpl();

    OrderService orderService = new OrderServiceImpl(orderRepository);

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
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
        List<Integer> itemNumber = new ArrayList<>();
        int choice;
        do {
            System.out.println("Enter the Item you want to add (0 to finish): ");
            choice = input.nextInt();
            itemNumber.add(choice);
        } while (choice!= 0);

        UUID orderID = orderService.createOrder(itemNumber);

        System.out.println("Order created with ID: " + orderID);

    }

    private static void showMenu() {
        List<MenuItem> menuItems = MenuItem.getMenu();
        System.out.println("Menu:");
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem.toString());
        }
    }
}