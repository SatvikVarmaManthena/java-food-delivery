package controller;

import bean.Order;
import service.FoodDeliveryService;

import java.util.List;

public class FoodDeliveryController {

    private static FoodDeliveryController instance;

    private FoodDeliveryService service;

    private FoodDeliveryController() {

        service =
                FoodDeliveryService.getInstance();
    }

    public static FoodDeliveryController getInstance() {

        if (instance == null) {
            instance = new FoodDeliveryController();
        }

        return instance;
    }

    public String registerCustomer(
            int customerId,
            String customerName,
            String phoneNumber,
            String address) {

        return service.registerCustomer(
                customerId,
                customerName,
                phoneNumber,
                address
        );
    }

    public String placeOrder(
            int customerId,
            String foodName,
            int quantity,
            double price) {

        return service.placeOrder(
                customerId,
                foodName,
                quantity,
                price
        );
    }

    public List<Order> viewCustomerOrders(
            int customerId) {

        return service.getCustomerOrders(customerId);
    }

    public String cancelOrder(int orderId) {

        return service.cancelOrder(orderId);
    }
}