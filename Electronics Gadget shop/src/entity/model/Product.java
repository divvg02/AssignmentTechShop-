package entity.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productName;
    private BigDecimal price; // Change to BigDecimal
    private String description;

    // Constructor
    public Product(int productId, String productName, BigDecimal price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price; // Keep as BigDecimal
        this.description = description;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter for price (returns BigDecimal)
    public BigDecimal getPrice() {
        return price;
    }

    // Setter for price (accepts BigDecimal)
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Product ID: " + productId +
                ", Name: " + productName +
                ", Price: " + price +
                ", Description: " + description;
    }
}