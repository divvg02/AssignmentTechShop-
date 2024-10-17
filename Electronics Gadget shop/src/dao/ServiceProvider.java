package dao;

import entity.model.Customer;
import entity.model.Inventory;
import entity.model.Order;
import entity.model.OrderDetail;
import entity.model.Product;

import java.util.List;

public interface ServiceProvider {
    // Customer methods
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    Customer getCustomer(int customerId);
    void deleteCustomer(int customerId);
    List<Customer> getAllCustomers();

    // Product methods
    void addProduct(Product product);
    void updateProduct(Product product);
    Product getProduct(int productId);
    void deleteProduct(int productId);
    List<Product> getAllProducts();

    // Order methods
    void addOrder(Order order);
    void updateOrder(Order order);
    Order getOrder(int orderId);
    void deleteOrder(int orderId);
    List<Order> getAllOrders();

    // OrderDetail methods
    void addOrderDetail(OrderDetail orderDetail);
    void updateOrderDetail(OrderDetail orderDetail);
    OrderDetail getOrderDetail(int orderDetailId);
    void deleteOrderDetail(int orderDetailId);
    List<OrderDetail> getAllOrderDetails();

    // Inventory methods
    void addInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    Inventory getInventory(int inventoryId);
    void deleteInventory(int inventoryId);
    List<Inventory> getAllInventories();

}