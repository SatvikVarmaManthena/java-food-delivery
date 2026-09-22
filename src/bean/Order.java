package bean;

public class Order {

    private int orderId;
    private int customerId;
    private String foodName;
    private int quantity;
    private double price;
    private double totalAmount;

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(int orderId, int customerId, String foodName,
                 int quantity, double price, double totalAmount) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
    }

    // Getters

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Setters

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // toString()

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", foodName='" + foodName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalAmount=" + totalAmount +
                '}';
    }
}