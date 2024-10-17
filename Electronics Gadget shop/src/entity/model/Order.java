package entity.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private int orderId;
    private int customerId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;

    // Constructor
    public Order(int orderId, int customerId, LocalDate orderDate, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Order ID: " + orderId +
                ", Customer ID: " + customerId +
                ", Order Date: " + orderDate +
                ", Total Amount: " + totalAmount;
    }
}