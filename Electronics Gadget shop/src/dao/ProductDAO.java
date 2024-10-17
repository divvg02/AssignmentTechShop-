package dao;

import entity.model.Product;
import entity.model.Customer;
import entity.model.Inventory;
import entity.model.Order;
import entity.model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements ServiceProvider {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (ProductID, ProductName, Price, Descript) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, product.getProductId());
            pstmt.setString(2, product.getProductName());
            pstmt.setBigDecimal(3, product.getPrice()); // Ensure product.getPrice() returns BigDecimal
            pstmt.setString(4, product.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET ProductName = ?, Price = ?, Descript = ? WHERE ProductID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setBigDecimal(2, product.getPrice()); // Ensure product.getPrice() returns BigDecimal
            pstmt.setString(3, product.getDescription());
            pstmt.setInt(4, product.getProductId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProduct(int productId) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE ProductID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getBigDecimal("Price"), // Ensure this is BigDecimal
                        rs.getString("Descript")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getBigDecimal("Price"), // Ensure this is BigDecimal
                        rs.getString("Descript")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE ProductID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Stub methods for Customer, Order, OrderDetail, and Inventory
    // ...

    @Override
    public void addCustomer(Customer customer) {
        // Not implemented
    }

    @Override
    public void updateCustomer(Customer customer) {
        // Not implemented
    }

    @Override
    public Customer getCustomer(int customerId) {
        return null; // Not implemented
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null; // Not implemented
    }

    @Override
    public void deleteCustomer(int customerId) {
        // Not implemented
    }

    @Override
    public void addOrder(Order order) {
        // Not implemented
    }

    @Override
    public void updateOrder(Order order) {
        // Not implemented
    }

    @Override
    public Order getOrder(int orderId) {
        return null; // Not implemented
    }

    @Override
    public List<Order> getAllOrders() {
        return null; // Not implemented
    }

    @Override
    public void deleteOrder(int orderId) {
        // Not implemented
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        // Not implemented
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        // Not implemented
    }

    @Override
    public OrderDetail getOrderDetail(int orderDetailId) {
        return null; // Not implemented
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return null; // Not implemented
    }

    @Override
    public void deleteOrderDetail(int orderDetailId) {
        // Not implemented
    }

    @Override
    public void addInventory(Inventory inventory) {
        // Not implemented
    }

    @Override
    public void updateInventory(Inventory inventory) {
        // Not implemented
    }

    @Override
    public Inventory getInventory(int inventoryId) {
        return null; // Not implemented
    }

    @Override
    public List<Inventory> getAllInventories() {
        return null; // Not implemented
    }

    @Override
    public void deleteInventory(int inventoryId) {
        // Not implemented
    }
}