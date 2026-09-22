package service;

import bean.Customer;
import bean.Order;
import repository.FoodDeliveryRepository;

import java.util.List;

public class FoodDeliveryService {

    private static FoodDeliveryService instance;

    private FoodDeliveryRepository repository;

    private FoodDeliveryService() {

        repository =
                FoodDeliveryRepository.getInstance();
    }

    public static FoodDeliveryService getInstance() {

        if (instance == null) {
            instance = new FoodDeliveryService();
        }

        return instance;
    }

    // REGISTER CUSTOMER

    public String registerCustomer(
            int customerId,
            String customerName,
            String phoneNumber,
            String address) {

        // Check duplicate ID
        if (repository.findCustomerById(customerId) != null) {

            return "Customer ID already exists.";
        }

        Customer customer =
                new Customer(
                        customerId,
                        customerName,
                        phoneNumber,
                        address
                );

        repository.addCustomer(customer);

        return "Customer registered successfully.";
    }

    // PLACE ORDER

    public String placeOrder(
            int customerId,
            String foodName,
            int quantity,
            double price) {

        // Check customer exists
        Customer customer =
                repository.findCustomerById(customerId);

        if (customer == null) {

            return "Customer does not exist.";
        }

        // Check quantity
        if (quantity <= 0) {

            return "Quantity must be greater than 0.";
        }

        // Calculate total
        double totalAmount =
                price * quantity;

        // Generate unique ID
        int orderId = generateOrderId();

        Order order =
                new Order(
                        orderId,
                        customerId,
                        foodName,
                        quantity,
                        price,
                        totalAmount
                );

        repository.addOrder(order);

        return "Order placed successfully. Order ID: "
                + orderId;
    }

    // VIEW ORDERS

    public List<Order> getCustomerOrders(int customerId) {

        return repository.getOrdersByCustomerId(customerId);
    }

    // CANCEL ORDER

    public String cancelOrder(int orderId) {

        boolean removed =
                repository.removeOrder(orderId);

        if (removed) {
            return "Order cancelled successfully.";
        }

        return "Order not found.";
    }

    // GENERATE UNIQUE ORDER ID

    private int generateOrderId() {

        int orderId = 101;

        while (repository.findOrderById(orderId) != null) {
            orderId++;
        }

        return orderId;
    }
}