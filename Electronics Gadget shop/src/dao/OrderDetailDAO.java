package dao;

import entity.model.Product;
import entity.model.Customer;
import entity.model.Inventory;
import entity.model.Order;
import entity.model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO implements ServiceProvider {
    private Connection connection;

    public OrderDetailDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO orderdetails (OrderDetailID, OrderID, ProductID, Quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderDetail.getOrderDetailId());
            pstmt.setInt(2, orderDetail.getOrderId());
            pstmt.setInt(3, orderDetail.getProductId());
            pstmt.setInt(4, orderDetail.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        String sql = "UPDATE orderdetails SET OrderID = ?, ProductID = ?, Quantity = ? WHERE OrderDetailID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setInt(2, orderDetail.getProductId());
            pstmt.setInt(3, orderDetail.getQuantity());
            pstmt.setInt(4, orderDetail.getOrderDetailId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderDetail getOrderDetail(int orderDetailId) {
        OrderDetail orderDetail = null;
        String sql = "SELECT * FROM orderdetails WHERE OrderDetailID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderDetailId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                orderDetail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM orderdetails";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity")
                );
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public void deleteOrderDetail(int orderDetailId) {
        String sql = "DELETE FROM orderdetails WHERE OrderDetailID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderDetailId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Stub methods for Customer, Product, Order, and Inventory
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
    public void addProduct(Product product) {
        // Not implemented
    }

    @Override
    public void updateProduct(Product product) {
        // Not implemented
    }

    @Override
    public Product getProduct(int productId) {
        return null; // Not implemented
    }

    @Override
    public List<Product> getAllProducts() {
        return null; // Not implemented
    }

    @Override
    public void deleteProduct(int productId) {
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