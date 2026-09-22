package ui;

import bean.Order;
import controller.FoodDeliveryController;
import factory.FoodDeliveryFactory;

import java.util.List;
import java.util.Scanner;

public class FoodDeliveryUI {

    private Scanner scanner;

    private FoodDeliveryController controller;

    public FoodDeliveryUI() {

        scanner = new Scanner(System.in);

        controller =
                FoodDeliveryFactory.getController();
    }

    public void start() {

        int choice;

        do {

            displayMenu();

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerCustomer();
                    break;

                case 2:
                    placeOrder();
                    break;

                case 3:
                    viewOrders();
                    break;

                case 4:
                    cancelOrder();
                    break;

                case 5:
                    System.out.println(
                            "Thank you for using Food Delivery System."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 5);

        scanner.close();
    }

    private void displayMenu() {

        System.out.println();
        System.out.println("================================");
        System.out.println("      FOOD DELIVERY SYSTEM");
        System.out.println("================================");
        System.out.println("1. Register Customer");
        System.out.println("2. Place Order");
        System.out.println("3. View My Orders");
        System.out.println("4. Cancel Order");
        System.out.println("5. Exit");
        System.out.println("================================");
    }

    private void registerCustomer() {

        System.out.println();
        System.out.println("----- REGISTER CUSTOMER -----");

        int customerId =
                readInt("Enter customer ID: ");

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        String result =
                controller.registerCustomer(
                        customerId,
                        name,
                        phone,
                        address
                );

        System.out.println(result);
    }

    private void placeOrder() {

        System.out.println();
        System.out.println("----- PLACE ORDER -----");

        int customerId =
                readInt("Enter customer ID: ");

        System.out.print("Enter food name: ");
        String foodName = scanner.nextLine();

        int quantity =
                readInt("Enter quantity: ");

        double price =
                readDouble("Enter price: ");

        String result =
                controller.placeOrder(
                        customerId,
                        foodName,
                        quantity,
                        price
                );

        System.out.println(result);
    }

    private void viewOrders() {

        System.out.println();
        System.out.println("----- MY ORDERS -----");

        int customerId =
                readInt("Enter customer ID: ");

        List<Order> orders =
                controller.viewCustomerOrders(customerId);

        if (orders.isEmpty()) {

            System.out.println(
                    "No orders found."
            );

            return;
        }

        for (Order order : orders) {

            System.out.println(order);
        }
    }

    private void cancelOrder() {

        System.out.println();
        System.out.println("----- CANCEL ORDER -----");

        int orderId =
                readInt("Enter order ID: ");

        String result =
                controller.cancelOrder(orderId);

        System.out.println(result);
    }

    private int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Enter a valid integer."
                );
            }
        }
    }

    private double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Enter a valid number."
                );
            }
        }
    }
}