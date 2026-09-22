package repository;

import bean.Customer;
import bean.Order;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoodDeliveryRepository {

    private static FoodDeliveryRepository instance;

    private List<Customer> customers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    private static final String CUSTOMER_FILE =
            "src/resources/customers.csv";

    private static final String ORDER_FILE =
            "src/resources/orders.csv";

    private FoodDeliveryRepository() {
        loadCustomersFromCSV();
        loadOrdersFromCSV();
    }

    public static FoodDeliveryRepository getInstance() {

        if (instance == null) {
            instance = new FoodDeliveryRepository();
        }

        return instance;
    }

    // CUSTOMER METHODS

    public void addCustomer(Customer customer) {

        customers.add(customer);
        saveCustomersToCSV();
    }

    public Customer findCustomerById(int customerId) {

        for (Customer customer : customers) {

            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    public List<Customer> getAllCustomers() {

        return customers;
    }

    // ORDER METHODS

    public void addOrder(Order order) {

        orders.add(order);
        saveOrdersToCSV();
    }

    public Order findOrderById(int orderId) {

        for (Order order : orders) {

            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }

    public List<Order> getOrdersByCustomerId(int customerId) {

        List<Order> customerOrders = new ArrayList<>();

        for (Order order : orders) {

            if (order.getCustomerId() == customerId) {
                customerOrders.add(order);
            }
        }

        return customerOrders;
    }

    // LOAD CUSTOMERS

    public void loadCustomersFromCSV() {

        customers.clear();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(CUSTOMER_FILE))) {

            String line;

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {

                    String[] data = line.split(",");

                    if (data.length != 4) {
                        continue;
                    }

                    int customerId =
                            Integer.parseInt(data[0].trim());

                    String customerName =
                            data[1].trim();

                    String phoneNumber =
                            data[2].trim();

                    String address =
                            data[3].trim();

                    Customer customer =
                            new Customer(
                                    customerId,
                                    customerName,
                                    phoneNumber,
                                    address
                            );

                    customers.add(customer);

                } catch (Exception e) {

                    System.out.println(
                            "Invalid customer row: " + line
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to read customers.csv"
            );
        }
    }

    // LOAD ORDERS

    public void loadOrdersFromCSV() {

        orders.clear();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(ORDER_FILE))) {

            String line;

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {

                    String[] data = line.split(",");

                    if (data.length != 6) {
                        continue;
                    }

                    int orderId =
                            Integer.parseInt(data[0].trim());

                    int customerId =
                            Integer.parseInt(data[1].trim());

                    String foodName =
                            data[2].trim();

                    int quantity =
                            Integer.parseInt(data[3].trim());

                    double price =
                            Double.parseDouble(data[4].trim());

                    double totalAmount =
                            Double.parseDouble(data[5].trim());

                    Order order =
                            new Order(
                                    orderId,
                                    customerId,
                                    foodName,
                                    quantity,
                                    price,
                                    totalAmount
                            );

                    orders.add(order);

                } catch (Exception e) {

                    System.out.println(
                            "Invalid order row: " + line
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to read orders.csv"
            );
        }
    }

    // SAVE CUSTOMERS

    public void saveCustomersToCSV() {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {

            writer.write(
                    "customerId,customerName,phoneNumber,address"
            );

            writer.newLine();

            for (Customer customer : customers) {

                writer.write(
                        customer.getCustomerId() + "," +
                                customer.getCustomerName() + "," +
                                customer.getPhoneNumber() + "," +
                                customer.getAddress()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to save customers.csv"
            );
        }
    }

    // SAVE ORDERS

    public void saveOrdersToCSV() {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(ORDER_FILE))) {

            writer.write(
                    "orderId,customerId,foodName,quantity,price,totalAmount"
            );

            writer.newLine();

            for (Order order : orders) {

                writer.write(
                        order.getOrderId() + "," +
                                order.getCustomerId() + "," +
                                order.getFoodName() + "," +
                                order.getQuantity() + "," +
                                order.getPrice() + "," +
                                order.getTotalAmount()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to save orders.csv"
            );
        }
    }

    // OPTIONAL CANCEL ORDER

    public boolean removeOrder(int orderId) {

        Order order = findOrderById(orderId);

        if (order == null) {
            return false;
        }

        orders.remove(order);

        saveOrdersToCSV();

        return true;
    }
}