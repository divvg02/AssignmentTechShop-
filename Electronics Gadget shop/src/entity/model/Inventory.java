package entity.model;

public class Inventory {
    private int inventoryId;
    private int productId;
    private int quantityInStock; // Change to match your DB

    // Constructor
    public Inventory(int inventoryId, int productId, int quantityInStock) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantityInStock = quantityInStock;
    }

    // Getters and Setters
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantityInStock() { // Update getter method
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) { // Update setter method
        this.quantityInStock = quantityInStock;
    }

    public String toString() {
        return "Inventory ID: " + inventoryId +
                ", Product ID: " + productId +
                ", Quantity In Stock: " + quantityInStock;
    }}