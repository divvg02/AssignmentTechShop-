package dao;

import entity.model.Inventory;
import entity.model.Customer;
import entity.model.Product;
import entity.model.Order;
import entity.model.OrderDetail;
import util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO implements ServiceProvider {
    private Connection connection;

    // Constructor accepting a Connection object
    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }
    // Method to add a new inventory record
    @Override
    public void addInventory(Inventory inventory) {
        String query = "INSERT INTO inventory (InventoryID, ProductID, QuantityInStock) VALUES (?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection("jdbc:mysql://localhost:3306/techshop", "root", "Susmi@833");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, inventory.getInventoryId());
            preparedStatement.setInt(2, inventory.getProductId());
            preparedStatement.setInt(3, inventory.getQuantityInStock());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing inventory record
    @Override
    public void updateInventory(Inventory inventory) {
        String query = "UPDATE inventory SET QuantityInStock = ? WHERE InventoryID = ?";
        try (Connection conn = DBConnUtil.getConnection("jdbc:mysql://localhost:3306/techshop", "root", "Susmi@833");
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, inventory.getQuantityInStock());
            statement.setInt(2, inventory.getInventoryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get an inventory record by ID
    @Override

    public Inventory getInventory(int inventoryId) {
        Inventory inventory = null;
        String query = "SELECT InventoryID, ProductID, QuantityInStock FROM inventory WHERE InventoryID = ?";

        try (Connection conn = DBConnUtil.getConnection("jdbc:mysql://localhost:3306/techshop", "root", "Susmi@833");
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, inventoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int productId = resultSet.getInt("ProductID");
                    int quantityInStock = resultSet.getInt("QuantityInStock"); // Use the correct column name

                    inventory = new Inventory(inventoryId, productId, quantityInStock);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventory; // Returns null if not found
    }
    // Method to get all inventory records
    @Override
    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = new ArrayList<>();
        String query = "SELECT * FROM inventory";
        try (Connection conn = DBConnUtil.getConnection("jdbc:mysql://localhost:3306/techshop", "root", "Susmi@833");
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Inventory inventory = new Inventory(
                        resultSet.getInt("InventoryID"),
                        resultSet.getInt("ProductID"),
                        resultSet.getInt("QuantityInStock") // Change to match your DB column name
                );
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventories;
    }

    // Method to delete an inventory record by ID
    @Override
    public void deleteInventory(int inventoryId) {
        String query = "DELETE FROM inventory WHERE InventoryID = ?";
        try (Connection conn = DBConnUtil.getConnection("jdbc:mysql://localhost:3306/techshop", "root", "Susmi@833");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, inventoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementing other methods from ServiceProvider (add other methods as needed)
    @Override
    public void addCustomer(Customer customer) {
        // Call CustomerDAO method
    }

    @Override
    public void updateCustomer(Customer customer) {
        // Call CustomerDAO method
    }

    @Override
    public Customer getCustomer(int customerId) {
        // Call CustomerDAO method
        return null;
    }

    @Override
    public void deleteCustomer(int customerId) {
        // Call CustomerDAO method
    }

    @Override
    public List<Customer> getAllCustomers() {
        // Call CustomerDAO method
        return null;
    }

    @Override
    public void addOrder(Order order) {
        // Call OrderDAO method
    }

    @Override
    public void updateOrder(Order order) {
        // Call OrderDAO method
    }

    @Override
    public Order getOrder(int orderId) {
        // Call OrderDAO method
        return null;
    }

    @Override
    public void deleteOrder(int orderId) {
        // Call OrderDAO method
    }

    @Override
    public List<Order> getAllOrders() {
        // Call OrderDAO method
        return null;
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        // Call OrderDetailDAO method
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        // Call OrderDetailDAO method
    }

    @Override
    public OrderDetail getOrderDetail(int orderDetailId) {
        // Call OrderDetailDAO method
        return null;
    }

    @Override
    public void deleteOrderDetail(int orderDetailId) {
        // Call OrderDetailDAO method
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        // Call OrderDetailDAO method
        return null;
    }

    @Override
    public void addProduct(Product product) {
        // Call ProductDAO method
    }

    @Override
    public void updateProduct(Product product) {
        // Call ProductDAO method
    }

    @Override
    public Product getProduct(int productId) {
        // Call ProductDAO method
        return null;
    }

    @Override
    public void deleteProduct(int productId) {
        // Call ProductDAO method
    }

    @Override
    public List<Product> getAllProducts() {
        // Call ProductDAO method
        return null;
    }
}