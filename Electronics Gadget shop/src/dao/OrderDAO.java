package dao;

import entity.model.Order;
import entity.model.Inventory;
import entity.model.Product;
import entity.model.Customer;
import entity.model.OrderDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO implements ServiceProvider {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // Order methods implementation
    @Override
    public void addOrder(Order order) {
        try {
            String query = "INSERT INTO orders (OrderID, CustomerID, OrderDate, TotalAmount) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(order.getOrderDate())); // Convert LocalDate to sql.Date
            preparedStatement.setBigDecimal(4, order.getTotalAmount()); // Ensure TotalAmount is BigDecimal
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Order order) {
        try {
            String query = "UPDATE orders SET CustomerID = ?, OrderDate = ?, TotalAmount = ? WHERE OrderID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(order.getOrderDate())); // Convert LocalDate to sql.Date
            preparedStatement.setBigDecimal(3, order.getTotalAmount()); // Ensure TotalAmount is BigDecimal
            preparedStatement.setInt(4, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        try {
            String query = "SELECT * FROM orders WHERE OrderID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order(
                        resultSet.getInt("OrderID"),
                        resultSet.getInt("CustomerID"),
                        resultSet.getDate("OrderDate").toLocalDate(), // Convert sql.Date to LocalDate
                        resultSet.getBigDecimal("TotalAmount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM orders";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("OrderID"),
                        resultSet.getInt("CustomerID"),
                        resultSet.getDate("OrderDate").toLocalDate(), // Convert sql.Date to LocalDate
                        resultSet.getBigDecimal("TotalAmount")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void deleteOrder(int orderId) {
        try {
            String query = "DELETE FROM orders WHERE OrderID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementing methods for OrderDetail
    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        try {
            String query = "INSERT INTO orderdetails (OrderDetailID, OrderID, ProductID, Quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderDetail.getOrderDetailId());
            preparedStatement.setInt(2, orderDetail.getOrderId());
            preparedStatement.setInt(3, orderDetail.getProductId());
            preparedStatement.setInt(4, orderDetail.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        try {
            String query = "UPDATE orderdetails SET OrderID = ?, ProductID = ?, Quantity = ? WHERE OrderDetailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderDetail.getOrderId());
            preparedStatement.setInt(2, orderDetail.getProductId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setInt(4, orderDetail.getOrderDetailId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderDetail getOrderDetail(int orderDetailId) {
        OrderDetail orderDetail = null;
        try {
            String query = "SELECT * FROM orderdetails WHERE OrderDetailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderDetailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderDetail = new OrderDetail(
                        resultSet.getInt("OrderDetailID"),
                        resultSet.getInt("OrderID"),
                        resultSet.getInt("ProductID"),
                        resultSet.getInt("Quantity")
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
        try {
            String query = "SELECT * FROM orderdetails";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        resultSet.getInt("OrderDetailID"),
                        resultSet.getInt("OrderID"),
                        resultSet.getInt("ProductID"),
                        resultSet.getInt("Quantity")
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
        try {
            String query = "DELETE FROM orderdetails WHERE OrderDetailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderDetailId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementing methods for Customer
    @Override
    public void addCustomer(Customer customer) {
        // Implement customer adding logic here
    }

    @Override
    public void updateCustomer(Customer customer) {
        // Implement customer updating logic here
    }

    @Override
    public Customer getCustomer(int customerId) {
        // Implement customer fetching logic here
        return null; // Placeholder return
    }

    @Override
    public List<Customer> getAllCustomers() {
        // Implement fetching all customers logic here
        return null; // Placeholder return
    }

    @Override
    public void deleteCustomer(int customerId) {
        try {
            String query = "DELETE FROM customers WHERE CustomerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementing methods for Product
    @Override
    public void addProduct(Product product) {
        try {
            String query = "INSERT INTO products (ProductID, ProductName, Price, Descript) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setBigDecimal(3, product.getPrice()); // Ensure Price is BigDecimal
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            String query = "UPDATE products SET ProductName = ?, Price = ?, Descript = ? WHERE ProductID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setBigDecimal(2, product.getPrice()); // Ensure Price is BigDecimal
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProduct(int productId) {
        Product product = null;
        try {
            String query = "SELECT * FROM products WHERE ProductID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("ProductID"),
                        resultSet.getString("ProductName"),
                        resultSet.getBigDecimal("Price"), // Ensure Price is BigDecimal
                        resultSet.getString("Descript")
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
        try {
            String query = "SELECT * FROM products";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("ProductID"),
                        resultSet.getString("ProductName"),
                        resultSet.getBigDecimal("Price"), // Ensure Price is BigDecimal
                        resultSet.getString("Descript")
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
        try {
            String query = "DELETE FROM products WHERE ProductID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementing methods for Inventory
    @Override
    public void addInventory(Inventory inventory) {
        try {
            String query = "INSERT INTO inventory (InventoryID, ProductID, QuantityAvailable) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inventory.getInventoryId());
            preparedStatement.setInt(2, inventory.getProductId());
            preparedStatement.setInt(3, inventory.getQuantityInStock());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInventory(Inventory inventory) {
        try {
            String query = "UPDATE inventory SET ProductID = ?, QuantityAvailable = ? WHERE InventoryID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inventory.getProductId());
            preparedStatement.setInt(2, inventory.getQuantityInStock());
            preparedStatement.setInt(3, inventory.getInventoryId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inventory getInventory(int inventoryId) {
        Inventory inventory = null;
        try {
            String query = "SELECT * FROM inventory WHERE InventoryID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inventoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                inventory = new Inventory(
                        resultSet.getInt("InventoryID"),
                        resultSet.getInt("ProductID"),
                        resultSet.getInt("QuantityAvailable")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    @Override
    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = new ArrayList<>();
        try {
            String query = "SELECT * FROM inventory";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Inventory inventory = new Inventory(
                        resultSet.getInt("InventoryID"),
                        resultSet.getInt("ProductID"),
                        resultSet.getInt("QuantityAvailable")
                );
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventories;
    }

    @Override
    public void deleteInventory(int inventoryId) {
        try {
            String query = "DELETE FROM inventory WHERE InventoryID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, inventoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}